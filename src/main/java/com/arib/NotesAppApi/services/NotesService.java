package com.arib.NotesAppApi.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.arib.NotesAppApi.dto.NotesDTO;
import com.arib.NotesAppApi.dto.NotesSaveDTO;
import com.arib.NotesAppApi.dto.NotesUpdateDTO;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.entities.Notes;

public interface NotesService {
	
	public List<NotesDTO> getAllNotes();

	public List<Notes> getFullNotes();

	public NotesDTO findById(int id);

	List<NotesDTO> getRecentNotes(int userID); //(with map)
	
	public List<Notes> getAllNotesOfUserId(int userID);

	ResponseEntity<ResponseMessage> save3(NotesSaveDTO note);

	ResponseEntity<ResponseMessage> update3(NotesUpdateDTO note);

	public ResponseEntity<HttpStatus> deleteById(int id);
	
//-- single combined DTO File

//	public ResponseEntity<ResponseMessage> save2(NotesDTO note);
//	ResponseEntity<ResponseMessage> update2(NotesDTO note);
	
//--------- Pagination
	
//	public Page<Notes> getNotesByPages(int page, int size);
	
//-------- IMPL 2

//	public Notes findById1(int id);
//	public List<NotesDTO> getTenNotes1(int userID);
//	public ResponseMessage save(NotesDTO note);
//	public ResponseMessage update(NotesDTO note);

}
