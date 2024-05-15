package com.arib.NotesAppApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class NotesResponse {

    String message;
    NotesDTO note;
    List<NotesDTO> pinned;
    List<NotesDTO> unpinned;
    List<NotesDTO> archived;
    List<NotesDTO> deleted;
    HttpStatus status;
    int statusCode;

}
