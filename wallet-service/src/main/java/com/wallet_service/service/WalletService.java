package com.wallet_service.service;

import com.wallet_service.client.UserClient;
import com.wallet_service.repository.WalletRepository;
import com.wallet_service.service.interactors.CreateWalletUseCase;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private WalletRepository walletRepository;
    private CreateWalletUseCase createWalletUseCase;

    public WalletService(WalletRepository walletRepository, UserClient userClient) {
        this.walletRepository = walletRepository;
        this.createWalletUseCase = new CreateWalletUseCase(walletRepository, userClient);
    }

    public void createWallet(String userId) {
        createWalletUseCase.createWallet(userId);
    }

    public Double getGetWalletBalance(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        var wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for user ID: " + userId));

        return wallet.getBalance();
    }

    public void updateWalletBalance(String userId, Double newBalance) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        if (newBalance < 0) {
            throw new IllegalArgumentException("New balance cannot be negative");
        }

        var wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for user ID: " + userId));

        wallet.setBalance(wallet.getBalance() + newBalance);
        walletRepository.save(wallet);
    }

}
