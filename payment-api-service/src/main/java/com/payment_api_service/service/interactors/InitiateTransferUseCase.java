package com.payment_api_service.service.interactors;

import com.payment_api_service.client.WalletClient;
import com.payment_api_service.producer.PaymentProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitiateTransferUseCase {

    private WalletClient walletClient;
    private PaymentProducer paymentProducer;

    public InitiateTransferUseCase(WalletClient walletClient, PaymentProducer paymentProducer) {
        this.walletClient = walletClient;
        this.paymentProducer = paymentProducer;
    }

    public void transfer(String destinationId, String sourceId, double amount) {

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
        if (!walletClient.exists(destinationId)) {
            throw new IllegalArgumentException("Destination user does not exist with ID: " + destinationId);
        }
        if (!walletClient.exists(sourceId)) {
            throw new IllegalArgumentException("Source user does not exist with ID: " + sourceId);
        }
        if(!walletClient.verifyAmount(sourceId, amount)){
            throw new IllegalArgumentException("Insufficient funds in source wallet for user ID: " + sourceId);
        }

        paymentProducer.processPayment(destinationId, sourceId, amount);
    }
}
