package com.payment_api_service.producer;

import com.payment_api_service.dto.TransferDto;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.name}")
    private String paymentExchange;

    @Value("${routing.key.transfer}")
    private String paymentRoutingKey ;

    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processPayment(String destinationId, String sourceId, double amount) {
        var dto = new TransferDto(destinationId, sourceId, amount);
        rabbitTemplate.convertAndSend(paymentExchange, paymentRoutingKey, dto);
    }

    public void sendPaymentNotification() {
        // Logic to send payment notification
    }
}


