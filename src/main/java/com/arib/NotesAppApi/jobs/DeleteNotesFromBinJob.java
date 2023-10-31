package com.arib.NotesAppApi.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.services.NotesService;

@Service
public class DeleteNotesFromBinJob {
	
	@Autowired
	private NotesService notesService;

	@Scheduled(cron = "0 0 0 * * *") // Run every midnight 000
	public void performDatabaseAction2() {
//		[TODO] Apply logic to delete at the exact moment
		notesService.deleteNotesFromTrash();
	}
	
}
