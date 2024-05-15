package com.arib.NotesAppApi.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.arib.NotesAppApi.dto.NotesDTO;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.entities.Notes;

public interface NotesService {

	public List<NotesDTO> getAllNotes();

	List<NotesDTO> getDeletedNotes(int userID);
	
	List<NotesDTO> getArchivedNotes(int userID);

	List<NotesDTO> getPinnedNotes(int userID);
	
	List<Notes> searchInArchived(int userID, String query);

	List<NotesDTO> searchInDashboard(int userID, String query);
	
	public void deleteNotesFromTrash();
	
//----- Bsic CRUD
	
	public NotesDTO findById(int id);

	public List<Notes> getAllNotesOfUserId(int userID);

	public ResponseEntity<ResponseMessage> addNotes(int userID, NotesDTO note);

	public ResponseEntity<ResponseMessage> updateNote(int noteID, NotesDTO note);

	public ResponseEntity<HttpStatus> deleteById(int id);

}
