package com.arib.NotesAppApi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arib.NotesAppApi.entities.Notes;

public interface NotesDao extends JpaRepository<Notes, Integer>{
	
	List<Notes> findAllByUser_idOrderByDateUpdatedDesc(int userID); //get all by date updated i.e. most recent edited comes first
	
	List<Notes> findAllByUser_idAndDeletedFalseAndPinnedFalseAndArchivedFalseOrderByDateUpdatedDesc(int userID); //get all undeleted, unarchived, unpinned notes
	
	List<Notes> findAllByUser_idAndDeletedTrueOrderByDateUpdatedDesc(int userID); //get all deleted notes
	
	List<Notes> findAllByUser_idAndPinnedTrueOrderByDateUpdatedDesc(int userID); //get all pinned notes
	
	List<Notes> findAllByUser_idAndArchivedTrueOrderByDateUpdatedDesc(int userID); //get all archived notes
	
	List<Notes> findTop10ByUser_idOrderByIdDesc(int userID);
	
//	//through native query
//
//	@Query(value = "SELECT * FROM notes n where n.user_id=:userID ORDER BY id DESC LIMIT 10", nativeQuery = true)
//	List<Notes> getTenNotes(int userID); 
//
//	@Query(value = "SELECT * FROM notes e WHERE e.user_id =:userID ORDER BY id DESC", nativeQuery = true)
//	List<Notes> getAllNotesOfUserId(int userID);
	
//	//Pagination
//	Page<Notes> findById(int id, Pageable pageable);
}
