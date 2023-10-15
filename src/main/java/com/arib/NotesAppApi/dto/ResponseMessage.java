package com.arib.NotesAppApi.dto;

import org.springframework.http.HttpStatus;

public record ResponseMessage(String message, HttpStatus status, int statusCode) {

	public ResponseMessage(String message, HttpStatus status) {
    this(message, status, status.value());
}

	public ResponseMessage(String message) {
	    this(message, HttpStatus.OK, 200); 
	}
}