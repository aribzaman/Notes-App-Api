package com.arib.NotesAppApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginWrapper {
	
	@Email
	@NotNull
	String email;
	
	@NotBlank
	String password;
}
