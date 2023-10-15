package com.arib.NotesAppApi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

public record NotesUpdateDTO(
        @Min(value = 1) @NotNull Integer id,
        String title,
        String content,
        Boolean deleted,
        Boolean archived,
        Boolean pinned
) {}
