package com.user_service.domain.dto;

public record CreateUserDTO(
        String nome,
        String email,
        String password,
        String role

) {
}