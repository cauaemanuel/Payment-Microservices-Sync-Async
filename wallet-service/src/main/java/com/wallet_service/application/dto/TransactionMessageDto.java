package com.wallet_service.application.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TransactionMessageDto {
    private UUID id;
    private String senderUserId;
    private String recipientUserId;
    private Double amount;

}