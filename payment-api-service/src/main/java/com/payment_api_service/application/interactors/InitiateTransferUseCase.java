package com.payment_api_service.application.interactors;

import com.payment_api_service.domain.client.WalletClient;
import com.payment_api_service.domain.entity.Transaction;
import com.payment_api_service.domain.repository.TransactionRepository;
import com.payment_api_service.domain.enums.TransactionStatus;
import com.payment_api_service.infrastructure.producer.PaymentProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class InitiateTransferUseCase {

    private WalletClient walletClient;
    private PaymentProducer paymentProducer;
    private TransactionRepository transactionRepository;

   public InitiateTransferUseCase(WalletClient walletClient, PaymentProducer paymentProducer, TransactionRepository transactionRepository) {
        this.walletClient = walletClient;
        this.paymentProducer = paymentProducer;
        this.transactionRepository = transactionRepository;
    }

    public void execute(String destinationId, String sourceId, double amount) {

        log.info("Initiating transfer from source ID: {} to destination ID: {} with amount: {}", sourceId, destinationId, amount);
        if (destinationId == null || destinationId.isEmpty()) {
            throw new IllegalArgumentException("Destination ID cannot be null or empty");
        }
        if (sourceId == null || sourceId.isEmpty()) {
            throw new IllegalArgumentException("Source ID cannot be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        var transaction = new Transaction();
        transaction.setSourceUserId(sourceId);
        transaction.setDestinationUserId(destinationId);
        transaction.setAmount(amount);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setCreatedAt(LocalDateTime.now());

        var transactionSave = transactionRepository.save(transaction);

        paymentProducer.processPayment(transactionSave);
    }
}
