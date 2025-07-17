package com.wallet_service.domain.service;

import com.wallet_service.application.dto.TransactionMessageDto;

public interface WalletService {

    Double getGetWalletBalance(String token);
    void updateWalletBalance(String token, Double newBalance);
    boolean verifyAmount(String email, Double amount);
    boolean isWalletExists(String email);
}
