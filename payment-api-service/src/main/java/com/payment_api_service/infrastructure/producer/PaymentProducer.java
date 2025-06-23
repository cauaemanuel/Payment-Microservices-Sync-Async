package com.payment_api_service.infrastructure.producer;

import com.payment_api_service.application.dto.TransactionMessageDto;
import com.payment_api_service.application.dto.TransferDto;
import com.payment_api_service.domain.entity.Transaction;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
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

    public void processPayment(Transaction transaction) {
        var transferDto = new TransactionMessageDto();
        transferDto.setId(transaction.getId());
        transferDto.setSenderUserId(transaction.getSourceUserId());
        transferDto.setRecipientUserId(transaction.getDestinationUserId());
        transferDto.setAmount(transaction.getAmount());

        rabbitTemplate.convertAndSend(paymentExchange, paymentRoutingKey, transferDto);
    }

}


