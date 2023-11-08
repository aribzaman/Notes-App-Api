package com.arib.NotesAppApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arib.NotesAppApi.dto.NotesDTO;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.entities.Notes;
import com.arib.NotesAppApi.services.NotesService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

@Validated
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/notes")
public class NotesController {

	private final NotesService notesService;

	// Get All details of all the Notes
	@GetMapping("/all")
	public List<Notes> getFullNotes() {
		return notesService.getFullNotes();
	}

	// Get All Notes
	@GetMapping("")
	public List<NotesDTO> getAllNotes() {
		return notesService.getAllNotes();
	}

	// Get Recent notes by userID
	@GetMapping(path = "/recent/{userID}")
	public List<NotesDTO> getRecentNotes(@PathVariable @Min(value = 1) int userID) {
		return notesService.getRecentNotes(userID);
	}

	// Get Deleted notes by userID
	@GetMapping(path = "/deleted/{userID}")
	public List<NotesDTO> getDeletedNotes(@PathVariable @Min(value = 1) int userID) {
		return notesService.getDeletedNotes(userID);
	}

	// Get Archived notes by userID
	@GetMapping(path = "/archived/{userID}")
	public List<NotesDTO> getArchivedNotes(@PathVariable @Min(value = 1) int userID) {
		return notesService.getArchivedNotes(userID);
	}

	// Get Pinned notes by userID
	@GetMapping(path = "/pinned/{userID}")
	public List<NotesDTO> getPinnedNotes(@PathVariable @Min(value = 1) int userID) {
		return notesService.getPinnedNotes(userID);
	}
	
	@GetMapping("/search/dashboard/{userID}")
    public List<Notes> searchInDashboard(@PathVariable @Min(value = 1) int userID, @RequestParam String query) {
        return notesService.searchInDashboard(userID, query);
    }
	
	@GetMapping("/search/archive/{userID}")
    public List<Notes> searchInArchived(@PathVariable @Min(value = 1) int userID, @RequestParam String query) {
        return notesService.searchInArchived(userID, query);
    }


	// -------- Basic Crud -------

	// get all notes of a userID
	@GetMapping(path = "/userId/{userID}")
	public List<Notes> getAllNotesOfUserId(@PathVariable @Min(value = 1) int userID) {
		return notesService.getAllNotesOfUserId(userID);
	}

	// get a note by id
	@GetMapping("/{id}")
	public NotesDTO getNote(@PathVariable @Min(value = 1) int id) {
		return notesService.findById(id);
	}

	// Create a note
	@PostMapping(path = "/{userID}", consumes = { "application/json" })
	public ResponseEntity<ResponseMessage> addNotes(@PathVariable @Min(value = 1) int userID,
			@Valid @RequestBody NotesDTO note) {
		System.out.println("contr : " + note);
		return notesService.addNotes(userID, note);
	}

	// Update a note
	@PutMapping(path = "/{noteID}", consumes = { "application/json" })
	public ResponseEntity<ResponseMessage> updateNote(@PathVariable @Min(value = 1) int noteID,
			@Valid @RequestBody NotesDTO note) {
		return notesService.updateNote(noteID, note);
	}

	// Delete a Note
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteNotes(@PathVariable @Min(value = 1) int id) {
		return notesService.deleteById(id);
	}

}
