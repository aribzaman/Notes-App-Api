package com.arib.NotesAppApi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus status, int statusCode, Date timestamp, String message, String description) {}