package com.payment_api_service.domain.messaging;

import com.payment_api_service.domain.entity.Transaction;

public interface PaymentEventPublisher {

    void processPayment(Transaction transaction);

}
