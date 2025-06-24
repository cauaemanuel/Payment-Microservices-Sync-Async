package com.payment_api_service.infrastructure.messaging;

import com.payment_api_service.application.dto.TransactionMessageDto;
import com.payment_api_service.application.interactors.RejectedTransactionUseCase;
import com.payment_api_service.application.interactors.SucessfulTransactionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitPaymentEventConsumer {

    private RejectedTransactionUseCase rejectedTransactionUseCase;
    private SucessfulTransactionUseCase sucessfulTransactionUseCase;

    public RabbitPaymentEventConsumer(RejectedTransactionUseCase rejectedTransactionUseCase, SucessfulTransactionUseCase sucessfulTransactionUseCase) {
        this.rejectedTransactionUseCase = rejectedTransactionUseCase;
        this.sucessfulTransactionUseCase = sucessfulTransactionUseCase;
    }

    @RabbitListener(queues = "payment.rejected")
    public void processRejectedTransaction(TransactionMessageDto transactionMessageDto) {
        log.info("Processing rejected transaction: {}", transactionMessageDto);
        rejectedTransactionUseCase.execute(transactionMessageDto);
    }

    @RabbitListener(queues = "payment.accepted")
    public void processSucessfulTransaction(TransactionMessageDto transactionMessageDto) {
        log.info("Processing successful transaction: {}", transactionMessageDto);
        sucessfulTransactionUseCase.execute(transactionMessageDto);
    }

}
