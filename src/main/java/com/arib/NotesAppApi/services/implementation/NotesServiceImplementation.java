package com.arib.NotesAppApi.services.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<NotesDTO> getAllNotes() {
		List<Notes> allNotes = notesDao.findAll();
		return allNotes.stream().map(nDTOm::applyReverse).collect(Collectors.toList());
	}

	@Override
	public List<NotesDTO> getDeletedNotes(int userID) {
		doesUserExists(userID);
		return notesDao.findAllByUser_idAndDeletedTrueOrderByDateUpdatedDesc(userID).stream().map(nDTOm::applyReverse).collect(Collectors.toList());	//nd.getTenNotes(userID)
	}

	@Override
	public List<NotesDTO> getPinnedNotes(int userID) {
		doesUserExists(userID);
		return notesDao.findAllByUser_idAndPinnedTrueOrderByDateUpdatedDesc(userID).stream().map(nDTOm::applyReverse).collect(Collectors.toList());
	}
	
	@Override
	public List<NotesDTO> getArchivedNotes(int userID) {
		doesUserExists(userID);
		return notesDao.findAllByUser_idAndArchivedTrueOrderByDateUpdatedDesc(userID).stream().map(nDTOm::applyReverse).collect(Collectors.toList());
	}
	
	@Override
    public List<NotesDTO> searchInDashboard(int userID, String query) {
		//[TODO] mapper space reject(trim)
		doesUserExists(userID);
		return notesDao.searchInDashboard(userID, query, query).stream().map(nDTOm::applyReverse).collect(Collectors.toList());

    }
	
	@Override
    public List<Notes> searchInArchived(int userID, String query) {
		//[TODO] mapper space reject(trim)
		doesUserExists(userID);
		return notesDao.searchInArchived(userID, query, query);

    }

	@Override
	public void deleteNotesFromTrash() {
	    LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
		List<Notes> notesToDelete = notesDao.findAllByDeletedTrueAndDateDeletedBeforeOrDateDeleted(thirtyDaysAgo, thirtyDaysAgo);
		notesDao.deleteAll(notesToDelete);
	}

	//----------------- CRUD
	
	@Override
	public List<Notes> getAllNotesOfUserId(int userID) {
		doesUserExists(userID);
		return notesDao.findAllByUser_idAndDeletedFalseAndPinnedFalseAndArchivedFalseOrderByDateUpdatedDesc(userID);
	}
	
	@Override
	public NotesDTO findById(int id) {
		doesNoteExists(id);
		Notes nt = notesDao.findById(id).get();
		return nDTOm.applyReverse(nt);
	}

	@Override
	public ResponseEntity<HttpStatus> deleteById(int id) {
		doesNoteExists(id);
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
		doesNoteExists(noteID);
		Notes newNote = nDTOm.update(noteID, note);
		notesDao.save(newNote);
		return ResponseEntity.ok().body(new ResponseMessage("Note Updated successfully!", HttpStatus.OK));
	}

	private void doesUserExists(int userID) {
		if (!userDao.existsById(userID))
			throw new ResourceNotFoundException("No user present with this ID");
	}

	private void doesNoteExists(int id) {
		if (!notesDao.existsById(id))
			throw new ResourceNotFoundException("No note present with this ID");
	}


}
