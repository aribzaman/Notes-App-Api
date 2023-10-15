package com.arib.NotesAppApi.services.implementation;

import java.util.List;
import java.util.Optional;

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

//	@Override
//	public User getUserByEmail(String email) {
//		return userDao.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("No user present with this email"));
//	}

//	@Override
//	public ResponseEntity<ResponseMessage> getLogin(String email, String password) {
//
//		Optional<User> user = userDao.findByEmail(email);
//		if(user.isPresent() && user.get().getPassword().equals(password)) {
//			return ResponseEntity.accepted().body(new ResponseMessage("success"));}
//		throw new InsufficientAuthenticationException("error");
//	}

//	@Override
//	public ResponseMessage getLogin(String email, String password) {
//	    
//		Optional<User> user = userDao.findByEmail(email);
//		String msg="No User Exists with this Email ID";
//	    if (user.isPresent()) {
//	    	if(user.get().getPassword().equals(password)) {
//	    		return new ResponseMessage("success");}
//	    	msg="Wrong Password!";
//	    }
//	    throw new InsufficientAuthenticationException(msg);
//	}
	
//	@Override
//	public User save(User user) {		
//		try {
//			userDao.save(user);
//		} catch (DataIntegrityViolationException e) {
//			throw new ConstraintViolationException("User Already Exists");
//		} catch (Exception e) {
//			System.out.println(e + "\n" + e.getClass());
//			throw new RuntimeErrorException(null);}
//		return user;
//
//	}



}
