package com.payment_api_service.application.interactors;

import com.payment_api_service.application.dto.TransactionMessageDto;
import com.payment_api_service.domain.entity.Transaction;
import com.payment_api_service.domain.enums.TransactionStatus;
import com.payment_api_service.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SucessfulTransactionUseCase {

    private TransactionRepository transactionRepository;

    public SucessfulTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(TransactionMessageDto dto){
        log.info("Processing successful transaction with ID: {}", dto.getId());
        Transaction transaction = transactionRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        log.info("Processing successful transaction: {}", dto);
        transaction.setStatus(TransactionStatus.APPROVED);
        transactionRepository.save(transaction);
    }
}
