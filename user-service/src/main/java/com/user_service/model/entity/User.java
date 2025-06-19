package com.user_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "tb_user")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Column(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.name());
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public enum UserRole {
        BUSINESS,
        CUSTOMER;

        @com.fasterxml.jackson.annotation.JsonCreator
        public static UserRole fromString(String value) {
            for (UserRole role : UserRole.values()) {
                if (role.name().equalsIgnoreCase(value)) {
                    return role;
                }
            }
            throw new IllegalArgumentException("Valor inválido para userRole: " + value +
                    ". Os valores válidos são: BUSINESS, CUSTOMER");
        }

        @com.fasterxml.jackson.annotation.JsonValue
        public String getValue() {
            return name();
        }
    }
}
