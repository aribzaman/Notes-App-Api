package com.arib.NotesAppApi.controller;

import java.util.List;
import java.util.stream.Collectors;

//import javax.validation.Valid;
//import javax.validation.constraints.Min;
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
import org.springframework.web.bind.annotation.RestController;

import com.arib.NotesAppApi.dto.NotesDTO;
import com.arib.NotesAppApi.dto.NotesSaveDTO;
import com.arib.NotesAppApi.dto.NotesUpdateDTO;
import com.arib.NotesAppApi.dto.ResponseMessage;
import com.arib.NotesAppApi.dao.NotesDao;
import com.arib.NotesAppApi.entities.Notes;
import com.arib.NotesAppApi.entities.Test;
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
	
	private final NotesDao nd;

	private final NotesService ns;

	
//----------------PLAYGROUND
//	@GetMapping(path = "")
//	public String test() {
//
////		System.out.println("controller me test me aaya"+ test);
////		return nd.findAllByUser_idAndDeletedTrueOrderByIdDesc(1);
//		
////		List<Notes> ls = nd.findAllByUser_idOrderByDateUpdatedDesc(1);
////		List<String> lstr = ls.stream().map((a)-> a.getDateUpdated().toString()).collect(Collectors.toList());
//		return "working";
//				
//////		return ns.save3(note);
////		return ResponseEntity.ok().body("yo");
//	}
//----------------

	// get a note by id
	@GetMapping("/{id}")
	public NotesDTO getNote(@PathVariable @Min(value = 1) int id) {
		return ns.findById(id);
	}
	
	// Get All Notes
	@GetMapping("")
	public List<NotesDTO> getAllNotes() {
		return ns.getAllNotes();
	}
	
	// Get All details of all the Notes
	@GetMapping("/all")
	public List<Notes> getFullNotes() {
		return ns.getFullNotes();
	}

	// get all notes of a userID
	@GetMapping(path = "/userId/{userID}")
	public List<Notes> getAllNotesOfUserId(@PathVariable @Min(value = 1) int userID) {
		return ns.getAllNotesOfUserId(userID);
	}

	// Get Recent notes by userID
	@GetMapping(path = "/recent/{userID}")
	public List<NotesDTO> getRecentNotes(@PathVariable @Min(value = 1) int userID) {
		return ns.getRecentNotes(userID);
	}
	
	//Create a note
	@PostMapping(path = "", consumes = { "application/json" })
	public ResponseEntity<ResponseMessage> addNotes(@Valid @RequestBody NotesSaveDTO note) {
		return ns.save3(note);
	}

	// Update a note
	@PutMapping(path = "", consumes = { "application/json" })
	public ResponseEntity<ResponseMessage> editNotes(@Valid @RequestBody NotesUpdateDTO note){
		return ns.update3(note);
	}

	// Delete a Note
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteNotes(@PathVariable @Min(value = 1) int id) {
		return ns.deleteById(id);
	}

}
