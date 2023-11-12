package com.arib.NotesAppApi.services;

import java.util.List;

import com.arib.NotesAppApi.dto.JWTResponseDTO;
import org.springframework.http.ResponseEntity;

import com.arib.NotesAppApi.dto.LoginWrapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.dto.UserDTO;
import com.arib.NotesAppApi.entities.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public User findById(int id);

	public UserDTO auth(LoginWrapper user); //new

	public ResponseEntity<ResponseMessage> save(User user);

//	//-----------
//
	public ResponseEntity<JWTResponseDTO> login(LoginWrapper request);

	
}
