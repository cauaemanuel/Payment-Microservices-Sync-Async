package com.wallet_service.service;

import com.wallet_service.infrastructure.client.UserClient;
import com.wallet_service.domain.entity.Wallet;
import com.wallet_service.infrastructure.repository.SpringJpaWalletRepository;
import com.wallet_service.application.interactors.CreateWalletUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateWalletUseCaseTest {

    private SpringJpaWalletRepository springJpaWalletRepository;
    private UserClient userClient;
    private CreateWalletUseCase createWalletUseCase;

    @BeforeEach
    void setUp() {
        springJpaWalletRepository = mock(SpringJpaWalletRepository.class);
        userClient = mock(UserClient.class);
        createWalletUseCase = new CreateWalletUseCase(springJpaWalletRepository, userClient);
    }

    @Test
    void testCreateWalletSuccess() {
        when(springJpaWalletRepository.existsByUserId("user1")).thenReturn(false);
        when(userClient.exists("user1")).thenReturn(true);

        createWalletUseCase.createWallet("user1");

        verify(springJpaWalletRepository).save(any(Wallet.class));
    }

    @Test
    void testCreateWalletThrowsForNullUserId() {
        assertThrows(IllegalArgumentException.class, () -> createWalletUseCase.createWallet(null));
    }

    @Test
    void testCreateWalletThrowsForEmptyUserId() {
        assertThrows(IllegalArgumentException.class, () -> createWalletUseCase.createWallet(""));
    }

    @Test
    void testCreateWalletThrowsIfWalletExists() {
        when(springJpaWalletRepository.existsByUserId("user1")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> createWalletUseCase.createWallet("user1"));
    }

    @Test
    void testCreateWalletThrowsIfUserNotExists() {
        when(springJpaWalletRepository.existsByUserId("user1")).thenReturn(false);
        when(userClient.exists("user1")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> createWalletUseCase.createWallet("user1"));
    }
}