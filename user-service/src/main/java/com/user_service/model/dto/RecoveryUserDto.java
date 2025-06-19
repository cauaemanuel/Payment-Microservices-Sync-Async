package com.user_service.model.dto;

import java.util.UUID;

public record RecoveryUserDto(

        UUID id,
        String email,
        String role

) {
}