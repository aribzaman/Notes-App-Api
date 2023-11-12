package com.arib.NotesAppApi.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import com.arib.NotesAppApi.dto.*;
import com.arib.NotesAppApi.security.JwtHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.dao.UserDao;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.exception.ConstraintViolationException;
import com.arib.NotesAppApi.exception.InsufficientAuthenticationException;
import com.arib.NotesAppApi.exception.ResourceNotFoundException;
import com.arib.NotesAppApi.services.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDTOMapper uDTOm;
	private final UserDao userDao;

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	private final AuthenticationManager manager;
	private final JwtHelper helper;

	@Override
	public List<User> getUsers() {
		List<User> ls = userDao.findAll();
		if (ls.isEmpty()) {
			throw new ResourceNotFoundException("No users found");
		}
		return ls;
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user present with this ID"));
	}
	
	@Override
	public UserDTO auth(LoginWrapper user) {
		Optional<User> userInDb = userDao.findByEmail(user.getEmail());
		String msg = "No User Exists with this Email ID";
		if (userInDb.isPresent()) {

			if (userInDb.get().getPassword().equals(user.getPassword())) {
				System.out.println(uDTOm.apply(userInDb.get()));
				return uDTOm.apply(userInDb.get());
			}
			msg = "Wrong Password!";
		}
		throw new InsufficientAuthenticationException(msg);
	}



	@Override
	public ResponseEntity<ResponseMessage> save(User user){
		try {
//			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.save(user);
		} 
		catch (DataIntegrityViolationException e) {
			throw new ConstraintViolationException("User Already Exists");
		}
		catch (Error e) {
			System.out.println(e + "\n" + e.getClass());
			throw new RuntimeErrorException(null);
		}
		return ResponseEntity.created(null).body(new ResponseMessage("User Added successfully!", HttpStatus.CREATED));

	}

//	//--------------

	public ResponseEntity<JWTResponseDTO> login(LoginWrapper request) {

//		this.doAuthenticate(request.getEmail(), request.getPassword());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		try {
			manager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);
		User user = userDao.findByEmail(request.getEmail()).get();
		JWTResponseDTO response = new JWTResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), userDetails.getUsername(), token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
