package com.arib.NotesAppApi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arib.NotesAppApi.entities.Notes;

public interface NotesDao extends JpaRepository<Notes, Integer>{
	
	List<Notes> findAllByUser_idOrderByDateUpdatedDesc(int userID); //get all by date updated i.e. most recent edited comes first
	
	List<Notes> findAllByUser_idAndDeletedFalseAndPinnedFalseAndArchivedFalseOrderByDateUpdatedDesc(int userID); //get all undeleted, unarchived, unpinned notes
	
	List<Notes> findAllByUser_idAndDeletedTrueOrderByDateUpdatedDesc(int userID); //get all deleted notes
	
	List<Notes> findAllByUser_idAndPinnedTrueOrderByDateUpdatedDesc(int userID); //get all pinned notes
	
	List<Notes> findAllByUser_idAndArchivedTrueOrderByDateUpdatedDesc(int userID); //get all archived notes
	
	List<Notes> findTop10ByUser_idOrderByIdDesc(int userID);

}
