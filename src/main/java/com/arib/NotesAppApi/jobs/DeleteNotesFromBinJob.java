package com.arib.NotesAppApi.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.dao.NotesDao;
import com.arib.NotesAppApi.entities.Notes;

@Service
public class DeleteNotesFromBinJob {

	@Autowired
	private NotesDao nd;

//	@Scheduled(cron = "*/10 * * * * *") // Run every 10 seconds (sec, min, hr, day, month, day of week)
	@Scheduled(fixedRate = 3600000)
	public void performDatabaseAction() {
//		System.out.println("Cron Started");
		List<Notes> allNotes = nd.findAll();
		int notesToKeep = Math.min(allNotes.size(), 10);
		List<Notes> notesToDelete = allNotes.subList(0, allNotes.size() - notesToKeep);
		for (Notes note : notesToDelete) {
//			System.out.println("deleting Note with id: "+ note.getId());
			nd.delete(note);
		}
	}

}
