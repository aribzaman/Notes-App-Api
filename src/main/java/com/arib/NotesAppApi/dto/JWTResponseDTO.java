package com.arib.NotesAppApi.dto;

public record JWTResponseDTO(int id, String firstName,
        String lastName, String email, String jwtToken) {

}
