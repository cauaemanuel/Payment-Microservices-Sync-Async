package com.wallet_service.infrastructure.repository;

import com.wallet_service.domain.entity.Wallet;
import com.wallet_service.domain.repository.WalletRepository;

import java.util.Optional;

public class WalletRepositoryImple implements WalletRepository {

    private final SpringJpaWalletRepository springJpaWalletRepository;

    public WalletRepositoryImple(SpringJpaWalletRepository springJpaWalletRepository) {
        this.springJpaWalletRepository = springJpaWalletRepository;
    }


    @Override
    public Optional<Wallet> save(Wallet wallet) {
        WalletEntity entity = toEntity(wallet);
        WalletEntity savedEntity = springJpaWalletRepository.save(entity);
        return Optional.of(toDomain(savedEntity));
    }

    @Override
    public Optional<Wallet> findByUserId(String userId) {
        return springJpaWalletRepository.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return springJpaWalletRepository.existsByUserId(userId);
    }

    Wallet toDomain(WalletEntity entity) {
        Wallet wallet = new Wallet();
        wallet.setId(entity.getId());
        wallet.setUserId(entity.getUserId());
        wallet.setBalance(entity.getBalance());
        return wallet;
    }

    WalletEntity toEntity(Wallet wallet) {
        WalletEntity entity = new WalletEntity();
        entity.setId(wallet.getId());
        entity.setUserId(wallet.getUserId());
        entity.setBalance(wallet.getBalance());
        return entity;
    }
}
