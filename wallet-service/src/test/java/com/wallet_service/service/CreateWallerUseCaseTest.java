package com.wallet_service.service;

import com.wallet_service.client.UserClient;
import com.wallet_service.model.entity.Wallet;
import com.wallet_service.repository.WalletRepository;
import com.wallet_service.service.interactors.CreateWalletUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateWalletUseCaseTest {

    private WalletRepository walletRepository;
    private UserClient userClient;
    private CreateWalletUseCase createWalletUseCase;

    @BeforeEach
    void setUp() {
        walletRepository = mock(WalletRepository.class);
        userClient = mock(UserClient.class);
        createWalletUseCase = new CreateWalletUseCase(walletRepository, userClient);
    }

    @Test
    void testCreateWalletSuccess() {
        when(walletRepository.existsByUserId("user1")).thenReturn(false);
        when(userClient.exists("user1")).thenReturn(true);

        createWalletUseCase.createWallet("user1");

        verify(walletRepository).save(any(Wallet.class));
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
        when(walletRepository.existsByUserId("user1")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> createWalletUseCase.createWallet("user1"));
    }

    @Test
    void testCreateWalletThrowsIfUserNotExists() {
        when(walletRepository.existsByUserId("user1")).thenReturn(false);
        when(userClient.exists("user1")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> createWalletUseCase.createWallet("user1"));
    }
}