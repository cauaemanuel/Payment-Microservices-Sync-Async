package com.wallet_service.domain.repository;

import com.wallet_service.domain.entity.Wallet;

import java.util.Optional;

public interface WalletRepository {

    Optional<Wallet> save(Wallet wallet);

    Optional<Wallet> findByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);
}
