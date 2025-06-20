package com.payment_api_service.controller;

import com.payment_api_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/transfer")
    public ResponseEntity transfer(@RequestParam String destinationId,
                                   @RequestParam String sourceId,
                                   @RequestParam double amount) {
        paymentService.initiateTransfer(destinationId, sourceId, amount);
        return ResponseEntity.ok("Payment transferred successfully");
    }
}
