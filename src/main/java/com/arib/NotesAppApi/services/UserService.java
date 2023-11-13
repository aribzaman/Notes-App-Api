package com.arib.NotesAppApi.services;

import com.arib.NotesAppApi.dto.JWTResponseDTO;
import org.springframework.http.ResponseEntity;

import com.arib.NotesAppApi.dto.LoginWrapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.entities.User;

public interface UserService {
	
	public ResponseEntity<ResponseMessage> save(User user);
	
	public ResponseEntity<JWTResponseDTO> login(LoginWrapper request);

	
}
