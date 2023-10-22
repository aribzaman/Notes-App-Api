package com.arib.NotesAppApi.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.dao.NotesDao;
import com.arib.NotesAppApi.dao.UserDao;
import com.arib.NotesAppApi.dto.NotesDTO;
import com.arib.NotesAppApi.dto.NotesDTOMapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.entities.Notes;
import com.arib.NotesAppApi.exception.ResourceNotFoundException;
import com.arib.NotesAppApi.services.NotesService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotesServiceImplementation implements NotesService {

	private final NotesDao notesDao;
	private final UserDao userDao;
	private final NotesDTOMapper nDTOm;

	@Override
	public List<Notes> getFullNotes() {
		return notesDao.findAll();
	}
	
	@Override
	public List<NotesDTO> getAllNotes() {
		List<Notes> allNotes = notesDao.findAll();
		return allNotes.stream().map(nDTOm::applyReverse).collect(Collectors.toList());
	}

	@Override
	public List<NotesDTO> getRecentNotes(int userID) {
		if (!userDao.existsById(userID))
			throw new ResourceNotFoundException("No user present with this ID");
		return notesDao.findTop10ByUser_idOrderByIdDesc(userID).stream().map(nDTOm::applyReverse).collect(Collectors.toList());	//nd.getTenNotes(userID)
	}

	@Override
	public List<NotesDTO> getDeletedNotes(int userID) {
		if (!userDao.existsById(userID))
			throw new ResourceNotFoundException("No user present with this ID");
		return notesDao.findAllByUser_idAndDeletedTrueOrderByDateUpdatedDesc(userID).stream().map(nDTOm::applyReverse).collect(Collectors.toList());	//nd.getTenNotes(userID)
	}
	
	@Override
	public List<NotesDTO> getPinnedNotes(int userID) {
		if (!userDao.existsById(userID))
			throw new ResourceNotFoundException("No user present with this ID");
		return notesDao.findAllByUser_idAndPinnedTrueOrderByDateUpdatedDesc(userID).stream().map(nDTOm::applyReverse).collect(Collectors.toList());	//nd.getTenNotes(userID)
	}
	
	@Override
	public List<NotesDTO> getArchivedNotes(int userID) {
		if (!userDao.existsById(userID))
			throw new ResourceNotFoundException("No user present with this ID");
		return notesDao.findAllByUser_idAndArchivedTrueOrderByDateUpdatedDesc(userID).stream().map(nDTOm::applyReverse).collect(Collectors.toList());	//nd.getTenNotes(userID)
	}

	//----------------- CRUD
	
	@Override
	public List<Notes> getAllNotesOfUserId(int userID) {
		if (!userDao.existsById(userID))
			throw new ResourceNotFoundException("No user present with this ID");
		return notesDao.findAllByUser_idAndDeletedFalseAndPinnedFalseAndArchivedFalseOrderByDateUpdatedDesc(userID);
	}
	
	@Override
	public NotesDTO findById(int id) {
		if (!notesDao.existsById(id))
			throw new ResourceNotFoundException("No note present with this ID");
		Notes nt = notesDao.findById(id).get();
		return nDTOm.applyReverse(nt);
	}

	@Override
	public ResponseEntity<HttpStatus> deleteById(int id) {
		if (!notesDao.existsById(id))
			throw new ResourceNotFoundException("No note present with this ID");
		notesDao.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<ResponseMessage> addNotes(int userID, NotesDTO note) {
		note.asSave();
		Notes newNote = nDTOm.save(userID, note);
		notesDao.save(newNote);
		return ResponseEntity.created(null).body(new ResponseMessage("Note Added successfully!", HttpStatus.CREATED));
	
	}
	
	@Override
	public ResponseEntity<ResponseMessage> updateNote(int noteID, NotesDTO note) {
		if (!notesDao.existsById(noteID))
			throw new ResourceNotFoundException("No note present with this ID");
		Notes newNote = nDTOm.update(noteID, note);
		notesDao.save(newNote);
		return ResponseEntity.ok().body(new ResponseMessage("Note Updated successfully!", HttpStatus.OK));
	}


}
