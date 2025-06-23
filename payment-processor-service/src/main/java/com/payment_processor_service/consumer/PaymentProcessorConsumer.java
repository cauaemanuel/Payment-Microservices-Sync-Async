package com.payment_processor_service.consumer;

import com.payment_processor_service.entity.TransactionMessageDto;
import com.payment_processor_service.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentProcessorConsumer {

    private PaymentService paymentService;

    public PaymentProcessorConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RabbitListener(queues = "payment.transfer") // Replace "myQueue" with your queue name
    public void receiveMessage(TransactionMessageDto transaction) {
        log.info("Received transaction: {}", transaction);
        paymentService.processPayment(transaction);
    }
}
