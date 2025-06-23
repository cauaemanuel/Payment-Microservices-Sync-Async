package com.wallet_service.infrastructure.consumer;

import com.wallet_service.application.dto.TransactionMessageDto;
import com.wallet_service.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WalletConsumer {

    private WalletService walletService;

    public WalletConsumer(WalletService walletService) {
        this.walletService = walletService;
    }


    @RabbitListener(queues = "wallet.transfer") // Replace "wallet.debit" with your queue name
    public void processTransaction(TransactionMessageDto transactionMessageDto){
        log.info("Received transaction: {}", transactionMessageDto);
        walletService.processTransaction(transactionMessageDto);
    }
}
