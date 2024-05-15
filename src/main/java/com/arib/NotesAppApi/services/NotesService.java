package com.arib.NotesAppApi.services;

import java.util.List;

import com.arib.NotesAppApi.dto.NotesResponse;
import com.arib.NotesAppApi.dto.NotesSearchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.arib.NotesAppApi.dto.NotesDTO;
import com.arib.NotesAppApi.entities.Notes;

public interface NotesService {

	List<NotesDTO> getAllNotes();
	
	void deleteNotesFromTrash();
	
//----- Basic CRUD
	
	NotesDTO findById(int id);

	List<Notes> getAllNotesOfUserId(int userID);

	ResponseEntity<NotesResponse> addNotes(int userID, NotesDTO note);

	ResponseEntity<NotesResponse> updateNote(int noteID, NotesDTO note);

	ResponseEntity<HttpStatus> deleteById(int id);

	NotesResponse getSectionNotes(int userID, String section);

	NotesSearchResponse searchNote(int userID, String query);
}
