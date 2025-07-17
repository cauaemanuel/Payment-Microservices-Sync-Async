package com.payment_api_service.domain.entity;

import com.payment_api_service.domain.enums.TransactionStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Transaction {

    private UUID id;
    private String sourceUserEmail;
    private String destinationUserEmail;
    private double amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;
}
