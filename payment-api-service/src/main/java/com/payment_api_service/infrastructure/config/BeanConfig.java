package com.payment_api_service.infrastructure.config;

import com.payment_api_service.domain.client.WalletClient;
import com.payment_api_service.domain.repository.TransactionRepository;
import com.payment_api_service.infrastructure.client.SpringWalletClient;
import com.payment_api_service.infrastructure.client.WalletClientImple;
import com.payment_api_service.infrastructure.persistence.SpringDataTransactionRepository;
import com.payment_api_service.infrastructure.persistence.TransactionRepositoryImple;
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
}
