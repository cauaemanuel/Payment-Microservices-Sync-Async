package com.payment_api_service.application.interactors;

import com.payment_api_service.application.dto.TransactionMessageDto;
import com.payment_api_service.domain.entity.Transaction;
import com.payment_api_service.domain.enums.TransactionStatus;
import com.payment_api_service.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionResultUseCase {

    private TransactionRepository transactionRepository;

    public TransactionResultUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(TransactionMessageDto dto, TransactionStatus status) {
        log.info("Processing transaction with ID: {}, Status: {}", dto.getId(), status);
        Transaction transaction = transactionRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        log.info("Processing transaction: {}", dto);
        transaction.setStatus(status);
        transactionRepository.save(transaction);
    }
}
