//package com.arib.NotesAppApi.dto;
//
//import java.util.Date;
//import java.util.function.Function;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.arib.NotesAppApi.dao.NotesDao;
//import com.arib.NotesAppApi.entities.Notes;
//import com.arib.NotesAppApi.exception.WrongDataTypeException;
//
//@Service
//public class NotesUpdateDTOMapper implements Function<NotesUpdateDTO, Notes>{
//
//	@Autowired
//	NotesDao notesDao;
//	
//	@Override
//	public Notes apply(NotesUpdateDTO t) {
//		Notes note= notesDao.findById(t.id()).get();
//		
//		if(t.title()!=null) {
//			if(t.title().isBlank()) {throw new WrongDataTypeException("{title=must not be blank}");}
//			if(!note.getTitle().equalsIgnoreCase(t.title())){
//				note.setTitle(t.title().strip()); note.setDateUpdated(new Date());}
//		}
//	
//		if(t.content()!=null && (note.getContent()==null || (note.getContent() != null && !note.getContent().equalsIgnoreCase(t.content())))) {
//			note.setContent(t.content().strip()); note.setDateUpdated(new Date());}
//		
//		if(t.pinned()!=null && note.isPinned()!=t.pinned()) {note.setArchived(false); note.setPinned(t.pinned());} //(pin or unpin) & remove from archive 
//		if(t.archived()!=null && note.isArchived()!=t.archived()) {note.setPinned(false); note.setArchived(t.archived());} //change arch and unpin
//		if(t.deleted()!=null && note.isDeleted()!=t.deleted()) {note.setDeleted(t.deleted()); note.setPinned(false); note.setArchived(false);} // move to delete & unpin+unarch
//		
//		return note;
//	}
//}