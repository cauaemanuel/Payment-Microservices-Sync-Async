package com.wallet_service.domain.service;

import com.wallet_service.application.dto.TransactionMessageDto;

public interface WalletService {

    Double getGetWalletBalance(String userId);
    void updateWalletBalance(String userId, Double newBalance);
    boolean verifyAmount(String userId, Double amount);
    boolean isWalletExists(String userId);
}
