package com.wallet_service.service;

import com.wallet_service.application.dto.TransactionMessageDto;
import com.wallet_service.infrastructure.client.UserClient;
import com.wallet_service.infrastructure.producer.WalletProducer;
import com.wallet_service.infrastructure.repository.SpringJpaWalletRepository;
import com.wallet_service.application.interactors.CreateWalletUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletService {

    private SpringJpaWalletRepository springJpaWalletRepository;
    private CreateWalletUseCase createWalletUseCase;

    private WalletProducer walletProducer;

    public WalletService(SpringJpaWalletRepository springJpaWalletRepository,
                        CreateWalletUseCase createWalletUseCase,
                        UserClient userClient,
                        WalletProducer walletProducer) {
        this.springJpaWalletRepository = springJpaWalletRepository;
        this.createWalletUseCase = createWalletUseCase;
        this.walletProducer = walletProducer;
    }

    public void createWallet(String userId) {
        createWalletUseCase.createWallet(userId);
    }

    public Double getGetWalletBalance(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        var wallet = springJpaWalletRepository.findByUserId(userId)
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

        var wallet = springJpaWalletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for user ID: " + userId));

        wallet.setBalance(wallet.getBalance() + newBalance);
        springJpaWalletRepository.save(wallet);
    }

    public boolean verifyAmount(String userId, Double amount) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        var wallet = springJpaWalletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for user ID: " + userId));

        return wallet.getBalance() >= amount;
    }

    public boolean isWalletExists(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return springJpaWalletRepository.existsByUserId(userId);
    }

    public void processTransaction(TransactionMessageDto dto){
        var sender = springJpaWalletRepository.findByUserId(dto.getSenderUserId());
        var receiver = springJpaWalletRepository.findByUserId(dto.getRecipientUserId());

        /**if (sender.isEmpty() || receiver.isEmpty()) {
            throw new IllegalArgumentException("Sender or receiver wallet not found");
            //enviar pra wallet failure
        }

        if (sender.get().getBalance() < dto.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in sender's wallet");
            //enviar pra wallet failure
        }

        // Deduct amount from sender's wallet
        sender.get().setBalance(sender.get().getBalance() - dto.getAmount());
        log.info("Deducted {} from sender's wallet. New balance: {}", dto.getAmount(), sender.get().getBalance());
        // Add amount to receiver's wallet

        receiver.get().setBalance(receiver.get().getBalance() + dto.getAmount());
        log.info("Added {} to receiver's wallet. New balance: {}", dto.getAmount(), receiver.get().getBalance());

        // Save both wallets
        springJpaWalletRepository.save(sender.get());
        springJpaWalletRepository.save(receiver.get());**/

        // coloca na fila de sucesso
        walletProducer.processSucessfulPayment(dto);
    }

}
