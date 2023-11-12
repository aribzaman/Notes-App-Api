package com.arib.NotesAppApi.controller;

import com.arib.NotesAppApi.dto.JWTResponseDTO;
import com.arib.NotesAppApi.dto.LoginWrapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.security.JwtHelper;
import com.arib.NotesAppApi.services.UserService;
import jakarta.validation.Valid;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

//---------------------------------

//http://localhost:9091/auth/login
//{"email": "arib","password": "arib"}
//{"jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmliIiwiaWF0IjoxNjk1MzQ1MjA2LCJleHAiOjE2OTUzNjMyMDZ9.jw63ZWCgy0c_9tSzTFcxrjCZ_FIF7XmxYSHI_EvkNc8l0dWXBDJ-dOPMEh18ZqeIpjgzfW6dCqwxvejUshD2RQ",
//    "username": "arib"}

//---------------------------------

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager manager;
    private final JwtHelper helper;
    private final UserService userService;

    public AuthController(UserDetailsService userDetailsService, AuthenticationManager manager, JwtHelper helper,
                          UserService userService) {
		super();
		this.userDetailsService = userDetailsService;
		this.manager = manager;
		this.helper = helper;
		this.userService = userService;
	}


//    @PostMapping("/login")
//    public ResponseEntity<JWTResponseDTO> login(@RequestBody LoginWrapper request) {
//
//        this.doAuthenticate(request.getEmail(), request.getPassword());
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        String token = this.helper.generateToken(userDetails);
//        JWTResponseDTO response = new JWTResponseDTO(token, userDetails.getUsername());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
        	//authenticates through manager
        	//The AuthenticationManager will internally use a configured UserDetailsService to fetch the user details based on the provided email (username).
        	//The UserDetailsService.loadUserByUsername method is called to retrieve the user details for the specified email. This method typically queries your user repository (e.g., a database) to find the user's information.
        	//Once the user details are obtained, Spring Security will compare the password provided in the UsernamePasswordAuthenticationToken with the password stored in the user details, using the PasswordEncoder that you configured earlier. If the passwords match, the authentication is successful; otherwise, a BadCredentialsException is thrown.
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        return "Credentials Invalid !!";
//    }

//	@PostMapping("/create-user")
//	public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }

    // Add New User
    @PostMapping(path = "/create-user", consumes = { "application/json" })
    public ResponseEntity<ResponseMessage> addUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }
    
	//principal represents current user
	@GetMapping("/current_user")
	public String getLoggedInUser(Principal principal)
	{
		System.out.println("Current User is "+principal.getName());
		return principal.getName();
	}


//--------------------
//
//    http://localhost:9091/auth/create-user
//    {
//        "firstName": "arib",
//        "email": "arib",
//        "password": "arib"
//    }
//
//    {
//        "userId": "e5622a97-e32b-4d21-b64b-bdd82d17e8b9",
//        "name": "arib",
//        "email": "arib",
//        "password": "$2a$10$SaH7RAO3eNRtYJQ9eScb7eeM7T7tyuYwdOLyu82b3epdmA1/eHXim",
//        "about": "hakuna matata",
//        "enabled": false,
//        "accountNonLocked": false,
//        "username": null,
//        "authorities": null,
//        "accountNonExpired": false,
//        "credentialsNonExpired": false
//    }

//--------------------

}
