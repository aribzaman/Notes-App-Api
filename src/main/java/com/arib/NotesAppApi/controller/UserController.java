package com.arib.NotesAppApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arib.NotesAppApi.dao.UserDao;
import com.arib.NotesAppApi.dto.LoginWrapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.dto.UserDTO;
import com.arib.NotesAppApi.dto.UserDTOMapper;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

@Validated
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

	private final UserService userService;

	// Get All Users
	@GetMapping("")
	public List<UserDTO> getUser() {
		return userService.getUsers();
	}

	// User Authentication new
	@PostMapping(path = "/login", consumes = { "application/json" })
	public UserDTO auth(@Valid @RequestBody LoginWrapper user) {
		return userService.auth(user);
	}

	// Add New User
	@PostMapping(path = "", consumes = { "application/json" })
	public ResponseEntity<ResponseMessage> addUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}

}
