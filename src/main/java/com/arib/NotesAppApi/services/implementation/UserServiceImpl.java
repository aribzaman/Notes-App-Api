package com.arib.NotesAppApi.services.implementation;

import com.arib.NotesAppApi.dto.*;
import com.arib.NotesAppApi.security.JwtHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.dao.UserDao;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.exception.BadCredentialsException;
import com.arib.NotesAppApi.exception.ConstraintViolationException;
import com.arib.NotesAppApi.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	private final AuthenticationManager manager;
	private final JwtHelper helper;

	@Override
	public ResponseEntity<NotesResponse> save(User user){
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.save(user);
		} 
		catch (DataIntegrityViolationException e) {
			throw new ConstraintViolationException("User Already Exists");
		}
		return ResponseEntity.created(null).body(NotesResponse.builder().message("User Added successfully!")
				.status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

	}

	public ResponseEntity<JWTResponseDTO> login(LoginWrapper request) {
		doAuthenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);
		User user = userDao.findByEmail(request.getEmail()).get();
		JWTResponseDTO response = new JWTResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), userDetails.getUsername(), token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		} catch (org.springframework.security.authentication.BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Password");
		}
	}

}
