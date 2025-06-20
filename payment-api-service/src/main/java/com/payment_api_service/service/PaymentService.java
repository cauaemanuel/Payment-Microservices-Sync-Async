package com.payment_api_service.service;

import com.payment_api_service.service.interactors.InitiateTransferUseCase;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private InitiateTransferUseCase initiateTransferUseCase;

    public PaymentService(InitiateTransferUseCase initiateTransferUseCase) {
        this.initiateTransferUseCase = initiateTransferUseCase;
    }

    public void initiateTransfer(String destinationId, String sourceId, double amount) {
        initiateTransferUseCase.transfer(destinationId, sourceId, amount);
    }

}
