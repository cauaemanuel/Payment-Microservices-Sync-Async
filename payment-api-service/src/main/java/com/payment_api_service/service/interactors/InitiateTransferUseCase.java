package com.payment_api_service.service.interactors;

import com.payment_api_service.client.UserClient;
import com.payment_api_service.client.WalletClient;
import com.payment_api_service.producer.PaymentProducer;

public class InitiateTransferUseCase {

    private UserClient userClient;
    private WalletClient WalletClient;
    private PaymentProducer paymentProducer;

    public void transfer(String destinationId, String sourceId, double amount) {

        if (destinationId == null || destinationId.isEmpty()) {
            throw new IllegalArgumentException("Destination ID cannot be null or empty");
        }
        if (sourceId == null || sourceId.isEmpty()) {
            throw new IllegalArgumentException("Source ID cannot be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (!userClient.exists(destinationId)) {
            throw new IllegalArgumentException("Destination user does not exist with ID: " + destinationId);
        }
        if (!userClient.exists(sourceId)) {
            throw new IllegalArgumentException("Source user does not exist with ID: " + sourceId);
        }
        // Logic to initiate the transfer would go here
        //walletVerify

        paymentProducer.processPayment(destinationId, sourceId, amount);
    }

}
