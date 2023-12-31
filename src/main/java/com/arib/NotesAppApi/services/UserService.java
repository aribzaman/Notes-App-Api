package com.arib.NotesAppApi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.arib.NotesAppApi.dto.LoginWrapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.dto.UserDTO;
import com.arib.NotesAppApi.entities.User;

public interface UserService {
	
	public List<UserDTO> getUsers();
	
	public User findById(int id);

	public UserDTO auth(LoginWrapper user);

	public ResponseEntity<ResponseMessage> save(User user);

	
}
