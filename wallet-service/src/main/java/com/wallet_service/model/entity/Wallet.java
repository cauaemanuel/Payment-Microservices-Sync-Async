package com.wallet_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_wallet")
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String userId;

    private Double balance;

}
