package com.payment_api_service.infrastructure.messaging;

import com.payment_api_service.application.dto.TransactionMessageDto;
import com.payment_api_service.application.interactors.TransactionResultUseCase;
import com.payment_api_service.domain.enums.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitPaymentEventConsumer {

    private TransactionResultUseCase transactionResultUseCase;

    @RabbitListener(queues = "payment.rejected")
    public void processRejectedTransaction(TransactionMessageDto transactionMessageDto) {
        log.info("Processing rejected transaction: {}", transactionMessageDto);
        transactionResultUseCase.execute(transactionMessageDto, TransactionStatus.REJECTED);
    }

    @RabbitListener(queues = "payment.accepted")
    public void processSucessfulTransaction(TransactionMessageDto transactionMessageDto) {
        log.info("Processing successful transaction: {}", transactionMessageDto);
        transactionResultUseCase.execute(transactionMessageDto, TransactionStatus.APPROVED);
    }

    @RabbitListener(queues = "payment.dlq")
    public void processFailedTransaction(TransactionMessageDto transactionMessageDto) {
        log.info("Processing failed transaction: {}", transactionMessageDto);
        transactionResultUseCase.execute(transactionMessageDto, TransactionStatus.FAILED);
    }
}
