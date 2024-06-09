package com.arib.NotesAppApi.controller;

import com.arib.NotesAppApi.dto.JWTResponseDTO;
import com.arib.NotesAppApi.dto.NotesResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arib.NotesAppApi.dto.LoginWrapper;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.services.UserService;

import jakarta.validation.Valid;

@Validated
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JWTResponseDTO> login(@RequestBody LoginWrapper request) {
		return userService.login(request);
	}
  
	@PostMapping
	public ResponseEntity<NotesResponse> addUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}

}
