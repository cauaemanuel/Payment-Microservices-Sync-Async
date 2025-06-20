package com.wallet_service.service.interactors;

import com.wallet_service.client.UserClient;
import com.wallet_service.model.entity.Wallet;
import com.wallet_service.repository.WalletRepository;

public class CreateWalletUseCase {

    private WalletRepository walletRepository;
    private UserClient userClient;

    public CreateWalletUseCase(WalletRepository walletRepository, UserClient userClient) {
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

        var wallet = new Wallet();
        wallet.setUserId(String.valueOf(userId));
        wallet.setBalance(0.0);
        walletRepository.save(wallet);
    }
}
