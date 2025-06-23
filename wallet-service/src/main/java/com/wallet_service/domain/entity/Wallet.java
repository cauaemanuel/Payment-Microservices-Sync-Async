package com.wallet_service.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

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
