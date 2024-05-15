package com.arib.NotesAppApi.jobs;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.services.NotesService;

@Service
@AllArgsConstructor
public class DeleteNotesFromBinJob {

	private NotesService notesService;

	@Scheduled(cron = "0 0 0 * * *") // Run every midnight 000
	public void performDatabaseAction2() {
		notesService.deleteNotesFromTrash();
	}
	
}
