package com.payment_api_service.application.interactors;

import com.payment_api_service.application.dto.TransactionMessageDto;
import com.payment_api_service.domain.enums.TransactionStatus;
import com.payment_api_service.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FailedTransactionUseCase {

    private TransactionRepository transactionRepository;

    public FailedTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(TransactionMessageDto dto) {
        log.info("Processing failed transaction: {}", dto);
        var transaction = transactionRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setStatus(TransactionStatus.FAILED);
        transactionRepository.save(transaction);
        log.info("Transaction marked as failed: {}", transaction);
    }
}
