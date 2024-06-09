package com.arib.NotesAppApi.dto;

import java.time.LocalDateTime;

import com.arib.NotesAppApi.exception.InvalidJsonDataException;

import jakarta.validation.constraints.Min;
import lombok.Builder;

@Builder
public record NotesDTO(@Min(value = 1) Integer id, String title, String content, String color, @Min(value = 1) Integer user,
		LocalDateTime dateCreated, LocalDateTime dateUpdated, LocalDateTime dateDeleted, Boolean deleted, Boolean archived, Boolean pinned) {

	public void asSave() {
		if (title == null || title.strip() == "") {
			throw new InvalidJsonDataException("Title must be provided for Saving a note");
		}
	}
}
