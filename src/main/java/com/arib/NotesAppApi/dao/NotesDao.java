package com.arib.NotesAppApi.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arib.NotesAppApi.entities.Notes;

public interface NotesDao extends JpaRepository<Notes, Integer>{
	
	List<Notes> findAllByUser_idOrderByDateUpdatedDesc(int userID); //get all by date updated i.e. most recent edited comes first
	
	List<Notes> findAllByUser_idAndDeletedFalseAndPinnedFalseAndArchivedFalseOrderByDateUpdatedDesc(int userID); //get all undeleted, unarchived, unpinned notes
	
	List<Notes> findAllByUser_idAndDeletedTrueOrderByDateUpdatedDesc(int userID); //get all deleted notes
	
	List<Notes> findAllByUser_idAndPinnedTrueOrderByDateUpdatedDesc(int userID); //get all pinned notes
	
	List<Notes> findAllByUser_idAndArchivedTrueOrderByDateUpdatedDesc(int userID); //get all archived notes

	List<Notes> findAllByDeletedTrueAndDateDeletedBeforeOrDateDeleted(LocalDateTime thirtyDaysAgo, LocalDateTime thirtyDaysAgo2);
	
	 @Query(value = "SELECT * FROM notes WHERE user_id = :userId AND archived=0 AND deleted=0 AND (LOWER(title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(content) LIKE LOWER(CONCAT('%', :query2, '%')))",
     nativeQuery = true)
List<Notes> searchInDashboard(
      @Param("userId") int userId,
      @Param("query") String query,
      @Param("query2") String query2
);
	 @Query(value = "SELECT * FROM notes WHERE user_id = :userId AND archived=1 AND deleted=0 AND (LOWER(title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(content) LIKE LOWER(CONCAT('%', :query2, '%')))",
		     nativeQuery = true)
		List<Notes> searchInArchived(
		      @Param("userId") int userId,
		      @Param("query") String query,
		      @Param("query2") String query2
		);
	 
	 
	 
}
