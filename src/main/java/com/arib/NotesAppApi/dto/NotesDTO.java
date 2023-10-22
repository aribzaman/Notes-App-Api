package com.arib.NotesAppApi.dto;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.arib.NotesAppApi.exception.InvalidJsonDataException;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record NotesDTO(@Min(value = 1) Integer id, String title, String content, @Min(value = 1) Integer user,
		Date dateCreated, Date dateUpdated, Boolean deleted, Boolean archived, Boolean pinned) {

	// Add a method to create an UpdateValidation instance
	public void asSave() {
		if (title == null || title.strip() == "") {
			throw new InvalidJsonDataException("Title must be provided for Saving a note");
		}
	}
}
