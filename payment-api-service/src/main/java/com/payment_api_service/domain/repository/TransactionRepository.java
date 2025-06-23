package com.payment_api_service.domain.repository;

import com.payment_api_service.domain.entity.Transaction;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
}
