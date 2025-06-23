package com.payment_api_service.infrastructure.controller;

import com.payment_api_service.application.interactors.InitiateTransferUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private InitiateTransferUseCase initiateTransferUseCase;

    public PaymentController(InitiateTransferUseCase initiateTransferUseCase) {
        this.initiateTransferUseCase = initiateTransferUseCase;
    }

    @PostMapping("/transfer")
    public ResponseEntity transfer(@RequestParam String destinationId,
                                   @RequestParam String sourceId,
                                   @RequestParam double amount) {
        initiateTransferUseCase.execute(destinationId, sourceId, amount);
        return ResponseEntity.ok("Payment transferred successfully");
    }
}
