package com.arib.NotesAppApi.dto;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.dao.NotesDao;
import com.arib.NotesAppApi.entities.Notes;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.exception.WrongDataTypeException;
import com.arib.NotesAppApi.services.UserService;

@Service
public class NotesDTOMapper implements Function<NotesDTO, Notes> {

	@Autowired
	private UserService us;

	@Autowired
	NotesDao notesDao;

	// save karne ke liye
	public Notes save(int userID, NotesDTO t) {
		User user = us.findById(userID);
		return new Notes(t.title(), t.content(), user, new Date());
	}

	public Notes update(int noteID, NotesDTO t) {

		Notes note = notesDao.findById(noteID).get();

		if (t.title() != null) {
			if (t.title().isBlank()) {
				throw new WrongDataTypeException("{title=must not be blank}");
			}
			if (!note.getTitle().equalsIgnoreCase(t.title())) {
				note.setTitle(t.title().strip());
				note.setDateUpdated(new Date());
			}
		}

		if (t.content() != null && (note.getContent() == null
				|| (note.getContent() != null && !note.getContent().equals(t.content())))) {
			note.setContent(t.content().strip());
			note.setDateUpdated(new Date());
		}

		if (t.pinned() != null && note.isPinned() != t.pinned()) {
			note.setArchived(false);
			note.setPinned(t.pinned());
		} // (pin or unpin) & remove from archive
		if (t.archived() != null && note.isArchived() != t.archived()) {
			note.setPinned(false);
//			note.setDeleted(false); //not the use case in app (from trash to archive)
			note.setArchived(t.archived());
		} // change arch and unpin
		if (t.deleted() != null && note.isDeleted() != t.deleted()) {
			note.setDeleted(t.deleted());
			note.setPinned(false);
			note.setArchived(false);
		} // move to delete & unpin+unarch

		return note;
	}

	public NotesDTO applyReverse(Notes note) {
		return new NotesDTO(note.getId(), note.getTitle(), note.getContent(), note.getUser().getId(), // just changing
																										// user to
																										// userId (int)
				note.getDateCreated(), note.getDateUpdated(), note.isDeleted(), note.isArchived(), note.isPinned());
	}

	@Override
	public Notes apply(NotesDTO t) {
		return null;
	}

}
