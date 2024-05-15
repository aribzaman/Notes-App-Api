package com.arib.NotesAppApi.services.implementation;

import java.time.LocalDateTime;
import java.util.List;

import com.arib.NotesAppApi.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.dao.NotesDao;
import com.arib.NotesAppApi.dao.UserDao;
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
		return notesDao.findAll().stream().map(nDTOm::applyReverse).toList();
	}

	@Override
	public NotesResponse getSectionNotes(int userId, String section) {
		doesUserExists(userId);
		return switch (section) {
			case "archived" -> {
				List<NotesDTO> ls = notesDao.findAllByUser_idAndArchivedTrueOrderByDateUpdatedDesc(userId)
						.stream().map(nDTOm::applyReverse).toList();
				yield NotesResponse.builder().archived(ls).status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build();
			}
			case "deleted" -> {
				List<NotesDTO> ls = notesDao.findAllByUser_idAndDeletedTrueOrderByDateUpdatedDesc(userId)
						.stream().map(nDTOm::applyReverse).toList();
				yield NotesResponse.builder().deleted(ls).status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build();
			}
			case "dashboard" -> {
				List<Notes> retrievedNotes = notesDao.findAllByUser_idAndDeletedFalseAndArchivedFalseOrderByDateUpdatedDesc(userId);

				List<NotesDTO> pinnedNotes = retrievedNotes.stream().filter(Notes::isPinned).map(nDTOm::applyReverse).toList();
				List<NotesDTO> unpinnedNotes = retrievedNotes.stream().filter(Notes::isNotPinned).map(nDTOm::applyReverse).toList();

				yield NotesResponse.builder().pinned(pinnedNotes).unpinned(unpinnedNotes)
						.status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build();
			}
			default -> throw new IllegalArgumentException("Section not found");
		};
	}

	@Override
	public NotesSearchResponse searchNote(int userID, String query) {

		doesUserExists(userID);
		List<Notes> retrievedNotes = notesDao.searchNote(userID, query);

		List<NotesDTO> notesFromDashboard = retrievedNotes.stream().filter(Notes::isNotArchived).map(nDTOm::applyReverse).toList();
		List<NotesDTO> notesFromArchive   = retrievedNotes.stream().filter(Notes::isArchived).map(nDTOm::applyReverse).toList();

		return NotesSearchResponse.builder().archived(notesFromArchive).dashboard(notesFromDashboard)
				.status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build();
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
	public ResponseEntity<NotesResponse> addNotes(int userID, NotesDTO note) {
		note.asSave();
		Notes newNote = nDTOm.save(userID, note);
		NotesDTO savedNote = nDTOm.applyReverse(notesDao.save(newNote));
		return ResponseEntity.created(null)
				.body(NotesResponse.builder().note(savedNote).message("Note Added successfully!")
						.status(HttpStatus.CREATED).statusCode(HttpStatus.CREATED.value()).build());
	}
	
	@Override
	public ResponseEntity<NotesResponse> updateNote(int noteID, NotesDTO note) {
		doesNoteExists(noteID);
		Notes newNote = nDTOm.update(noteID, note);
		notesDao.save(newNote);
		return ResponseEntity.ok().body(NotesResponse.builder().message("Note Updated successfully!")
				.status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
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