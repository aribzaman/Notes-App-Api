package com.arib.NotesAppApi.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
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
import com.arib.NotesAppApi.dto.NotesSaveDTO;
import com.arib.NotesAppApi.dto.NotesSaveDTOMapper;
import com.arib.NotesAppApi.dto.NotesUpdateDTO;
import com.arib.NotesAppApi.dto.NotesUpdateDTOMapper;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.entities.Notes;
import com.arib.NotesAppApi.exception.ResourceNotFoundException;
import com.arib.NotesAppApi.services.NotesService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotesServiceImplementation implements NotesService {

	private final NotesDTOMapper nDTOm;
	private final NotesSaveDTOMapper nsDTOm;
	private final NotesUpdateDTOMapper nuDTOm;
	private final NotesDao nd;
	private final UserDao userDao;

	@Override
	public List<Notes> getFullNotes() {
		return nd.findAll();
	}
	
	@Override
	public List<NotesDTO> getAllNotes() {
		List<Notes> allNotes = nd.findAll();
		return allNotes.stream().map(nDTOm).collect(Collectors.toList());
	}

	@Override
	public List<NotesDTO> getRecentNotes(int userID) {
		if (!userDao.existsById(userID))
			throw new ResourceNotFoundException("No user present with this ID");
		return nd.findTop10ByUser_idOrderByIdDesc(userID).stream().map(nDTOm).collect(Collectors.toList());	//nd.getTenNotes(userID)
	}

	@Override
	public List<Notes> getAllNotesOfUserId(int userID) {
		if (!userDao.existsById(userID))
			throw new ResourceNotFoundException("No user present with this ID");
		return nd.findAllByUser_idAndDeletedFalseAndPinnedFalseAndArchivedFalseOrderByDateUpdatedDesc(userID);
	}

	@Override
	public NotesDTO findById(int id) {
		if (!nd.existsById(id))
			throw new ResourceNotFoundException("No note present with this ID");
		Notes nt = nd.findById(id).get();
		return nDTOm.apply(nt);
	}

	@Override
	public ResponseEntity<HttpStatus> deleteById(int id) {
		if (!nd.existsById(id))
			throw new ResourceNotFoundException("No note present with this ID");
		nd.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	

	@Override
	public ResponseEntity<ResponseMessage> save3(NotesSaveDTO note) {
		Notes newNote = nsDTOm.apply(note);
		nd.save(newNote);
		return ResponseEntity.created(null).body(new ResponseMessage("Note Added successfully!", HttpStatus.CREATED));
	}

	@Override
	public ResponseEntity<ResponseMessage> update3(NotesUpdateDTO note) {
		if (!nd.existsById(note.id()))
			throw new ResourceNotFoundException("No note present with this ID");
		Notes newNote = nuDTOm.apply(note);
		nd.save(newNote);
		return ResponseEntity.ok().body(new ResponseMessage("Note Updated successfully!", HttpStatus.OK));
	}

}
