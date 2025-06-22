// domain/model/User.java
package com.user_service.domain.entity;

import lombok.Data;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private UserRole role;
}