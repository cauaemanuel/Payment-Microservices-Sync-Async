package com.payment_api_service.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String PAYMENT_EXCHANGE = "payment";
    public static final String PAYMENT_ACCEPTED_QUEUE = "payment.accepted";
    public static final String PAYMENT_REJECTED_QUEUE = "payment.rejected";
    public static final String PAYMENT_TRANSFER_QUEUE = "payment.transfer";
    public static final String PAYMENT_DLQ = "payment.dlq";
    public static final String WALLET_EXCHANGE = "wallet";
    public static final String WALLET_TRANSFER_QUEUE = "wallet.transfer";


    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(PAYMENT_EXCHANGE);
    }

    @Bean
    public Queue paymentAcceptedQueue() {
        return QueueBuilder.durable(PAYMENT_ACCEPTED_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", PAYMENT_DLQ)
                .withArgument("x-queue-type", "quorum")
                .withArgument("x-delivery-limit", 5)
                .build();
    }

    @Bean
    public Queue paymentRejectedQueue() {
        return QueueBuilder.durable(PAYMENT_REJECTED_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", PAYMENT_DLQ)
                .withArgument("x-queue-type", "quorum")
                .withArgument("x-delivery-limit", 5)
                .build();
    }

    @Bean
    public Queue paymentTransferQueue() {
        return QueueBuilder.durable(PAYMENT_TRANSFER_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-queue-type", "quorum")
                .withArgument("x-dead-letter-routing-key", PAYMENT_DLQ)
                .withArgument("x-delivery-limit", 5)
                .build();
    }

    @Bean
    public Queue paymentDlq() {
        return QueueBuilder.durable(PAYMENT_DLQ).build();
    }

    @Bean
    public Binding acceptedBinding() {
        return BindingBuilder.bind(paymentAcceptedQueue())
                .to(paymentExchange())
                .with("accepted");
    }

    @Bean
    public Binding rejectedBinding() {
        return BindingBuilder.bind(paymentRejectedQueue())
                .to(paymentExchange())
                .with("rejected");
    }

    @Bean
    public Binding transferBinding() {
        return BindingBuilder.bind(paymentTransferQueue())
                .to(paymentExchange())
                .with("transfer");
    }

    // Exchange wallet
    @Bean
    public DirectExchange walletExchange() {
        return new DirectExchange(WALLET_EXCHANGE);
    }

    // Fila wallet.transfer com DLQ
    @Bean
    public Queue walletTransferQueue() {
        return QueueBuilder.durable(WALLET_TRANSFER_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", PAYMENT_DLQ)
                .withArgument("x-queue-type", "quorum")
                .withArgument("x-delivery-limit", 5)
                .build();
    }

    // Binding wallet.transfer -> wallet exchange
    @Bean
    public Binding walletTransferBinding() {
        return BindingBuilder.bind(walletTransferQueue())
                .to(walletExchange())
                .with("transfer");
    }
}