package com.wallet_service.service;

import com.wallet_service.client.UserClient;
import com.wallet_service.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {

    private WalletRepository walletRepository;
    private UserClient userClient;

    public WalletService(WalletRepository walletRepository, UserClient userClient) {
        this.walletRepository = walletRepository;
        this.userClient = userClient;
    }

    public void createWallet(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        if (walletRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("Wallet already exists for user ID: " + userId);
        }

        if(!userClient.exists(userId)){
            throw new IllegalArgumentException("User does not exist with ID: " + userId);
        }

        var wallet = new com.wallet_service.model.entity.Wallet();
        wallet.setUserId(String.valueOf(userId));
        wallet.setBalance(0.0);
        walletRepository.save(wallet);
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
