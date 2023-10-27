package com.arib.NotesAppApi.controller;

import java.util.List;

//import javax.validation.Valid;
//import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arib.NotesAppApi.dto.LoginWrapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.dto.UserDTO;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

	private UserService us;

	public UserController(UserService us) {
		super();
		this.us = us;
	}

	// Get All Users
	@GetMapping("")
	public List<User> getUser() {
		return us.getUsers();
	}

	//PUT validation for other than numbers - @Validated @Pattern(regexp = "\\d+", message = "Invalid user ID format. Please provide a valid integer ID.") 
	// get a user by id 
	@GetMapping("/{id}")
	public User getUser(@PathVariable @Min(value = 1) int id) {
		return us.findById(id);
	}

	// User Authentication new
	@PostMapping(path = "/auth", consumes = { "application/json" })
	public UserDTO auth(@Valid @RequestBody LoginWrapper user) {
		return us.auth(user);
	}

	// Add New User
	@PostMapping(path = "", consumes = { "application/json" })
	public ResponseEntity<ResponseMessage> addUser(@Valid @RequestBody User user) {
		return us.save(user);
	}

}
