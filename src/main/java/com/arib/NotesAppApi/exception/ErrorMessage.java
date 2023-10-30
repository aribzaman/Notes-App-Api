package com.arib.NotesAppApi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus status, int statusCode, LocalDateTime timestamp, String message, String description) {}