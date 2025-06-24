package com.wallet_service.infrastructure.service;
import com.wallet_service.domain.messaging.WalletEventPublisher;
import com.wallet_service.domain.repository.WalletRepository;
import com.wallet_service.domain.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletServiceImple implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletEventPublisher walletEventPublisher;

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

    public boolean verifyAmount(String userId, Double amount) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        var wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for user ID: " + userId));

        return wallet.getBalance() >= amount;
    }

    public boolean isWalletExists(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return walletRepository.existsByUserId(userId);
    }



}
