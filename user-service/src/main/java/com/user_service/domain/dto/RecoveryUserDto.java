package com.user_service.domain.dto;

import java.util.UUID;

public record RecoveryUserDto(

        UUID id,
        String email,
        String role

) {
}