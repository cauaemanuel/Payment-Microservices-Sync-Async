package com.payment_api_service.service;

import com.payment_api_service.client.WalletClient;
import com.payment_api_service.producer.PaymentProducer;
import com.payment_api_service.service.interactors.InitiateTransferUseCase;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private InitiateTransferUseCase initiateTransferUseCase;

    public PaymentService(WalletClient walletClient,
                          PaymentProducer paymentProducer) {
        this.initiateTransferUseCase = new InitiateTransferUseCase(walletClient, paymentProducer);
    }

    public void initiateTransfer(String destinationId, String sourceId, double amount) {
        initiateTransferUseCase.transfer(destinationId, sourceId, amount);
    }

}
