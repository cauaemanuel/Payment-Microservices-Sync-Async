package com.payment_api_service.application.interactors;

import com.payment_api_service.application.dto.TransactionMessageDto;
import com.payment_api_service.domain.entity.Transaction;
import com.payment_api_service.domain.enums.TransactionStatus;
import com.payment_api_service.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RejectedTransactionUseCase {

    private TransactionRepository transactionRepository;

    public RejectedTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(TransactionMessageDto dto){
        log.info("Processing rejected transaction with ID: {}", dto.getId());
        Transaction transaction = transactionRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        log.info("Transaction found: {}", transaction);
        transaction.setStatus(TransactionStatus.REJECTED);
        transactionRepository.save(transaction);
    }
}
