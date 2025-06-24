package com.wallet_service.infrastructure.messaging;

import com.wallet_service.application.dto.TransactionMessageDto;
import com.wallet_service.application.interactors.ProcessTransactionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitWalletEventConsumer {

    private ProcessTransactionUseCase processTransactionUseCase;

    public RabbitWalletEventConsumer(ProcessTransactionUseCase processTransactionUseCase) {
        this.processTransactionUseCase = processTransactionUseCase;
    }

    @RabbitListener(queues = "wallet.transfer") // Replace "wallet.debit" with your queue name
    public void processTransaction(TransactionMessageDto transactionMessageDto){
        log.info("Received transaction: {}", transactionMessageDto);
        processTransactionUseCase.processTransaction(transactionMessageDto);
    }
}
