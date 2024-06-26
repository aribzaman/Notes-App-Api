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
public class NotesSearchResponse {

    List<NotesDTO> dashboard;
    List<NotesDTO> archived;
    HttpStatus status;
    int statusCode;

}