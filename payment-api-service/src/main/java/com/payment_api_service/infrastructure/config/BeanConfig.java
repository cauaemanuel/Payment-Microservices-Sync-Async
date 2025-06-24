package com.payment_api_service.infrastructure.config;

import com.payment_api_service.application.interactors.RejectedTransactionUseCase;
import com.payment_api_service.application.interactors.SucessfulTransactionUseCase;
import com.payment_api_service.domain.client.WalletClient;
import com.payment_api_service.domain.messaging.PaymentEventPublisher;
import com.payment_api_service.domain.repository.TransactionRepository;
import com.payment_api_service.infrastructure.client.SpringWalletClient;
import com.payment_api_service.infrastructure.client.WalletClientImple;
import com.payment_api_service.infrastructure.messaging.RabbitPaymentEventConsumer;
import com.payment_api_service.infrastructure.messaging.RabbitPaymentEventPublisher;
import com.payment_api_service.infrastructure.persistence.SpringDataTransactionRepository;
import com.payment_api_service.infrastructure.persistence.TransactionRepositoryImple;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public TransactionRepository transactionRepository(SpringDataTransactionRepository springDataTransactionRepository) {
        return new TransactionRepositoryImple(springDataTransactionRepository);
    }

    @Bean
    public WalletClient walletClient(SpringWalletClient springWalletClient){
        return new WalletClientImple(springWalletClient);
    }

    @Bean
    public PaymentEventPublisher paymentEventPublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitPaymentEventPublisher(rabbitTemplate);
    }

}
