package com.payment_api_service.infrastructure.persistence;

import com.payment_api_service.domain.entity.Transaction;
import com.payment_api_service.domain.repository.TransactionRepository;

public class TransactionRepositoryImple implements TransactionRepository {

    private SpringDataTransactionRepository springDataTransactionRepository;

    public TransactionRepositoryImple(SpringDataTransactionRepository springDataTransactionRepository) {
        this.springDataTransactionRepository = springDataTransactionRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = toEntity(transaction);
        TransactionEntity savedEntity = springDataTransactionRepository.save(entity);
        return toDomain(savedEntity);
    }

    Transaction toDomain(TransactionEntity entity) {
        Transaction transaction = new Transaction();
        transaction.setId(entity.getId());
        transaction.setSourceUserId(entity.getSourceUserId());
        transaction.setDestinationUserId(entity.getDestinationUserId());
        transaction.setAmount(entity.getAmount());
        transaction.setStatus(entity.getStatus());
        transaction.setFailureReason(entity.getFailureReason());
        transaction.setCreatedAt(entity.getCreatedAt());
        return transaction;
    }

    TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setSourceUserId(transaction.getSourceUserId());
        entity.setDestinationUserId(transaction.getDestinationUserId());
        entity.setAmount(transaction.getAmount());
        entity.setStatus(transaction.getStatus());
        entity.setFailureReason(transaction.getFailureReason());
        entity.setCreatedAt(transaction.getCreatedAt());
        return entity;
    }
}
