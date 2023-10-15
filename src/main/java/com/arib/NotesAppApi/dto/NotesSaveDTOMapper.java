package com.arib.NotesAppApi.dto;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.entities.Notes;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.services.UserService;

@Service
public class NotesSaveDTOMapper implements Function<NotesSaveDTO, Notes>{

	@Autowired
	private UserService us;
	
	@Override
	public Notes apply(NotesSaveDTO t) {
		User user = us.findById(t.user());
		return new Notes(t.title().strip(), (t.content()!=null)? t.content().strip() : null, user, new Date());
	}
	
}
