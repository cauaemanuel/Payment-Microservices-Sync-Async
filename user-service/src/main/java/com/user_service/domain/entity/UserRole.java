package com.user_service.domain.entity;

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