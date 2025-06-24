package com.wallet_service.infrastructure.config;

import com.wallet_service.domain.messaging.WalletEventPublisher;
import com.wallet_service.domain.repository.WalletRepository;
import com.wallet_service.domain.service.WalletService;
import com.wallet_service.infrastructure.messaging.RabbitWalletEventPublisher;
import com.wallet_service.infrastructure.repository.SpringJpaWalletRepository;
import com.wallet_service.infrastructure.repository.WalletRepositoryImple;
import com.wallet_service.infrastructure.service.WalletServiceImple;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public WalletEventPublisher walletEventPublisher(RabbitTemplate rabbitTemplate){
        return new RabbitWalletEventPublisher(rabbitTemplate);
    }

    @Bean
    public WalletRepository walletRepository(SpringJpaWalletRepository springJpaWalletRepository){
        return new WalletRepositoryImple(springJpaWalletRepository);
    }

    @Bean
    public WalletService walletService(WalletRepository walletRepository, WalletEventPublisher walletEventPublisher) {
        return new WalletServiceImple(walletRepository, walletEventPublisher);
    }
}
