package com.user_service.application.dto;

public record CreateUserDTO(
        String nome,
        String email,
        String password,
        String role

) {
}