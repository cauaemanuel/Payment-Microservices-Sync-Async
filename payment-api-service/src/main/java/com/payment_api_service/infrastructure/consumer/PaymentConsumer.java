package com.payment_api_service.infrastructure.consumer;

import com.payment_api_service.application.dto.TransactionMessageDto;
import com.payment_api_service.application.interactors.RejectedTransactionUseCase;
import com.payment_api_service.application.interactors.SucessfulTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentConsumer {

    private RejectedTransactionUseCase rejectedTransactionUseCase;
    private SucessfulTransaction sucessfulTransaction;

    public PaymentConsumer(RejectedTransactionUseCase rejectedTransactionUseCase, SucessfulTransaction sucessfulTransaction) {
        this.rejectedTransactionUseCase = rejectedTransactionUseCase;
        this.sucessfulTransaction = sucessfulTransaction;
    }

    @RabbitListener(queues = "payment.rejected")
    public void processRejectedTransaction(TransactionMessageDto transactionMessageDto) {
        log.info("Processing rejected transaction: {}", transactionMessageDto);
        rejectedTransactionUseCase.execute(transactionMessageDto);
    }

    @RabbitListener(queues = "payment.accepted")
    public void processSucessfulTransaction(TransactionMessageDto transactionMessageDto) {
        log.info("Processing successful transaction: {}", transactionMessageDto);
        sucessfulTransaction.execute(transactionMessageDto);
    }

}
