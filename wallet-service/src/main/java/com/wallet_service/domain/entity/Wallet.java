package com.wallet_service.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
public class Wallet {


    private UUID id;

    private String userEmail;

    private Double balance;

}
