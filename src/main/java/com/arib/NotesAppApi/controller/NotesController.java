package com.arib.NotesAppApi.controller;

import java.util.List;

import com.arib.NotesAppApi.dto.NotesResponse;
import com.arib.NotesAppApi.dto.NotesSearchResponse;
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

	@GetMapping
	public List<NotesDTO> getAllNotes() {
		return notesService.getAllNotes();
	} //Dev

	@GetMapping("/section/{userId}")
	public NotesResponse getNotes(@PathVariable @Min(value = 1) int userId, @RequestParam String section) {
		return notesService.getSectionNotes(userId, section);
	}

	@GetMapping("/search/{userId}")
	public NotesSearchResponse searchNote2(@PathVariable @Min(value = 1) int userId, @RequestParam String query) {
		return notesService.searchNote(userId, query);
	}

	// -------- Basic Crud -------

	@GetMapping("/userId/{userId}")
	public List<Notes> getAllNotesOfUserId(@PathVariable @Min(value = 1) int userId) {
		return notesService.getAllNotesOfUserId(userId);
	}

	// get a note by id
	@GetMapping("/{noteId}")
	public NotesDTO getNote(@PathVariable @Min(value = 1) int noteId) {
		return notesService.findById(noteId);
	}

	// Create a note
	@PostMapping("/{userId}")
	public ResponseEntity<NotesResponse> addNotes(@PathVariable @Min(value = 1) int userId,
			@Valid @RequestBody NotesDTO note) {
		return notesService.addNotes(userId, note);
	}

	// Update a note
	@PutMapping("/{noteId}")
	public ResponseEntity<NotesResponse> updateNote(@PathVariable @Min(value = 1) int noteId,
			@Valid @RequestBody NotesDTO note) {
		return notesService.updateNote(noteId, note);
	}

	// Delete a Note
	@DeleteMapping("/{noteId}")
	public ResponseEntity<HttpStatus> deleteNotes(@PathVariable @Min(value = 1) int noteId) {
		return notesService.deleteById(noteId);
	}

}
