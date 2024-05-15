package com.arib.NotesAppApi.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorMessage(HttpStatus status, int statusCode, LocalDateTime timestamp, String message, String description) {}