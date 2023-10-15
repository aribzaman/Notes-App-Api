package com.arib.NotesAppApi.dto;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.entities.Notes;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.services.UserService;

@Service
public class NotesDTOMapper implements Function<Notes, NotesDTO>{
	
	@Autowired
	private UserService us;

	@Override
	public NotesDTO apply(Notes note) {
		return new NotesDTO(
				note.getId(),
				note.getTitle(),
				note.getContent(),
				note.getUser().getId(),
				note.getDateCreated(),
				note.getDateUpdated(),
				note.isDeleted(),
				note.isArchived(),
				note.isPinned()
				);
	}
	
//	@Override
//	public NotesDTO apply(Notes note) {
//		return new NotesDTO(
//				note.getId(),
//				note.getTitle(),
//				note.getContent()
//				);
//	}
	
	// Method to perform reverse mapping (NotesDTO to Notes) for Saving a new note
    public Notes applyReverse(NotesDTO notesDTO) {
    	User user = us.findById(notesDTO.user());
		return new Notes(notesDTO.title(), notesDTO.content(), user, new Date());
    }
    
 // Method to perform reverse mapping with an additional id parameter (USED AS AN UPDATING DTO)
    public Notes applyReverse(NotesDTO notesDTO, int id) {
        User user = us.findById(notesDTO.user());
//        System.out.println("user in appylRev "+ user);
        return new Notes(id, notesDTO.title(), notesDTO.content(), user, new Date(), notesDTO.deleted(), notesDTO.archived(), notesDTO.pinned());
    }

}
