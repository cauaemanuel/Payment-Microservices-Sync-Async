package com.user_service.model.dto;

public record CreateUserDTO(
        String nome,
        String email,
        String password,
        String role

) {
}