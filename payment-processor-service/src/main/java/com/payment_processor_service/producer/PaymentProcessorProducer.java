package com.payment_processor_service.producer;

import com.payment_processor_service.entity.TransactionMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentProcessorProducer {

    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.name}")
    private String paymentExchange;

    @Value("${routing.key.rejected}")
    private String paymentRejectedRoutingKey ;

    @Value("${routing.key.accepted}")
    private String paymentAcceptedRoutingKey;

    public PaymentProcessorProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAcceptedTransactionMessage(TransactionMessageDto dto) {
        log.info("Sending accepted transaction message: {}", dto);
        log.debug("Sending message to exchange: {}, routing key: {}, message: {}", paymentExchange, paymentAcceptedRoutingKey, dto);
        rabbitTemplate.convertAndSend(paymentExchange, paymentAcceptedRoutingKey, dto);
    }

    public void sendRejectedTransactionMessage(TransactionMessageDto dto) {
        log.info("Sending rejected transaction message: {}", dto);
        log.debug("Sending message to exchange: {}, routing key: {}, message: {}", paymentExchange, paymentRejectedRoutingKey, dto);
        rabbitTemplate.convertAndSend(paymentExchange, paymentRejectedRoutingKey, dto);
    }
}
