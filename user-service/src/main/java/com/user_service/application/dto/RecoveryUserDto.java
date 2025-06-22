package com.user_service.application.dto;

import java.util.UUID;

public record RecoveryUserDto(

        UUID id,
        String email,
        String role

) {
}