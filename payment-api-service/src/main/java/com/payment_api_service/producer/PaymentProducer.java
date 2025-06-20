package com.payment_api_service.producer;

import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {


    public void processPayment(String destinationId, String sourceId, double amount) {
        // Logic to process payment
    }

    public void sendPaymentNotification() {
        // Logic to send payment notification
    }
}
