package com.payment_processor_service.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class TransactionMessageDto {
    private UUID id;
    private String senderUserEmail;
    private String recipientUserEmail;
    private Double amount;
}