//package com.arib.NotesAppApi.Controller;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ExitTestApi.DTO.NotesDTO;
//import com.ExitTestApi.DTO.ResponseMessage;
//import com.ExitTestApi.dao.NotesDao;
//import com.ExitTestApi.entities.Notes;
//import com.ExitTestApi.services.NotesService;
//
//@RestController
//@CrossOrigin
//@RequestMapping(value = "/v1/notes")
//public class NotesControllerV1 {
//	
//	@Autowired
//	private NotesDao nd;
//
//	private final NotesService ns;
//
//	public NotesControllerV1(NotesService ns) {
//		super();
//		this.ns = ns;
//	}
//	
//	// get a note by id
//	@GetMapping("/{id}")
//	public Notes getNote(@PathVariable int id) {
//		return ns.findById(id);
//	}
//	
//	// Add New Note
//	@PostMapping(path = "", consumes = { "application/json" }) // make it reponse
//	public ResponseMessage addNotes(@RequestBody NotesDTO note) {
//		return ns.save(note);
//	}
//	
//	// Update a note
//	@PutMapping(path = "", consumes = { "application/json" }) // return respo entity
//	public ResponseMessage editNotes(@RequestBody NotesDTO note) {
//		return ns.update(note);
//	}
//	
////	------------  Combined dto file
//	// Add New Note 
//	@PostMapping(path = "", consumes = { "application/json" }) // make it reponse
//	public ResponseEntity<ResponseMessage> addNotes(@Valid @RequestBody NotesDTO note) {
//		System.out.println("controller me notesDTO me aaya"+ note);
//		return ns.save2(note);
//	}
//	// Update a note
//	@PutMapping(path = "", consumes = { "application/json" }) // return respo entity
//	public ResponseEntity<ResponseMessage> editNotes(@Valid @RequestBody NotesDTO note) {
//		System.out.println("control= " + note);
//		
//		return ns.update2(note);
//	}
//	
//	
//	
//}

