package com.arib.NotesAppApi.dto;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

////a dto for updating notes in db, where id is not sent by user since it is automatically assigned
//public record NotesDTO (@Min(value = 0) @NotNull Integer id, @NotBlank String title, String content, @Min(value = 1) @NotNull Integer user, Date dateCreated, Date dateUpdated) {
//	
////	public NotesDTO(@NotNull String title, String content, Integer user, Date dateCreated, Date dateUpdated) {
////	    this(-1, title, content, user, dateCreated, dateUpdated); 
////	}
//}
//
////a dto for saving notes in db, where only title, content and user is given by user
//public record NotesDTO2 (@NotBlank String title, String content, @Min(value = 1) @NotNull Integer user) {}


public record NotesDTO(
        @Min(value = 0, groups = UpdateValidation.class) Integer id,
        @NotBlank String title,
        String content,
        @Min(value = 1) @NotNull Integer user,
        Date dateCreated,
        Date dateUpdated,
        boolean deleted,
        boolean archived,
        boolean pinned
) {
    // Define a validation group for update
    public interface UpdateValidation {}
    
    // Define a constructor for creating a new note
    public NotesDTO(@NotBlank String title, String content, @Min(value = 1) @NotNull Integer user, Date dateCreated, Date dateUpdated, boolean deleted,
            boolean archived,
            boolean pinned) {
        this(null, title, content, user, dateCreated, dateUpdated, deleted, archived, pinned);
    }

//    // Define a method to check if it's an update DTO
//    public boolean isUpdate() {
//        return id != null;
//    }

    // Add a method to create an UpdateValidation instance
    public NotesDTO asUpdate() {
        if (id == null) {
            throw new IllegalArgumentException("Note ID must be provided for updating note");
        }
        return this;
    }
}

