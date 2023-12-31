package com.arib.NotesAppApi.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.dto.LoginWrapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.dto.UserDTO;
import com.arib.NotesAppApi.dto.UserDTOMapper;
import com.arib.NotesAppApi.dao.UserDao;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.exception.ConstraintViolationException;
import com.arib.NotesAppApi.exception.InsufficientAuthenticationException;
import com.arib.NotesAppApi.exception.ResourceNotFoundException;
import com.arib.NotesAppApi.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDTOMapper uDTOm;
	private final UserDao userDao;

	@Override
	public List<UserDTO> getUsers() {
		List<User> ls = userDao.findAll();
		if (ls.isEmpty()) {
			throw new ResourceNotFoundException("No users found");
		}
		return ls.stream().map(uDTOm::apply).collect(Collectors.toList());
				
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

}
