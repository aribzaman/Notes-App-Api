package com.arib.NotesAppApi.dto;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.exception.InvalidJsonDataException;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Min(value = 1) Integer id;
//	String title;
//	String content;
//	@Min(value = 1) Integer user;
//	Boolean deleted;
//	Boolean archived;
//	Boolean pinned;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public record NotesDTO (
	@Min(value = 1) Integer id,
	String title,
	String content,
	@Min(value = 1) Integer user,
	Date dateCreated,
	Date dateUpdated,
	Boolean deleted,
	Boolean archived,
	Boolean pinned )
{

	// Add a method to create an UpdateValidation instance
	public void asUpdate() { if (id == null) {
			throw new InvalidJsonDataException("Note ID must be provided for updating note");}}
	
	// Add a method to create an UpdateValidation instance
	public void asSave() {
		String msg = (title==null || title.strip()=="")?"and title " : "";
		if (user == null) {
			throw new InvalidJsonDataException("User ID " + msg + "must be provided for Saving note");}
		}

}