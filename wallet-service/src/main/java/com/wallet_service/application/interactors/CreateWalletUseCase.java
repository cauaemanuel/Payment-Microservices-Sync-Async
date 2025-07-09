package com.wallet_service.application.interactors;

import com.wallet_service.domain.repository.WalletRepository;
import com.wallet_service.infrastructure.client.UserClient;
import com.wallet_service.domain.entity.Wallet;
import com.wallet_service.infrastructure.repository.SpringJpaWalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Component
public class CreateWalletUseCase {

    private WalletRepository walletRepository;
    private UserClient userClient;

    public CreateWalletUseCase(WalletRepository walletRepository, UserClient userClient) {
        this.walletRepository = walletRepository;
        this.userClient = userClient;
    }

    public void createWallet(String userid) {

        if (userid == null || userid.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID cannot be null or empty");
        }
        // Validate userId
        var userId = UUID.fromString(userid);

        if (walletRepository.existsByUserId(userid)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wallet already exists for user ID: ");
        }

        if(!userClient.exists(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId);
        }

        var wallet = new Wallet();
        wallet.setUserId(String.valueOf(userId));
        wallet.setBalance(0.0);
        walletRepository.save(wallet);
    }

}
