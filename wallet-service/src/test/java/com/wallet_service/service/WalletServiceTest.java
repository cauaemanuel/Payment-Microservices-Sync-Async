package com.wallet_service.service;

import com.wallet_service.infrastructure.service.WalletServiceImple;
import com.wallet_service.infrastructure.client.UserClient;
import com.wallet_service.domain.entity.Wallet;
import com.wallet_service.infrastructure.repository.SpringJpaWalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WalletServiceTest {

    private SpringJpaWalletRepository springJpaWalletRepository;
    private UserClient userClient;
    private WalletServiceImple walletServiceImple;

    @BeforeEach
    void setUp() {
        springJpaWalletRepository = mock(SpringJpaWalletRepository.class);
        userClient = mock(UserClient.class);
        walletServiceImple = new WalletServiceImple(springJpaWalletRepository, userClient);
    }

    @Test
    void testCreateWalletDelegatesToUseCase() {
        when(userClient.exists("user1")).thenReturn(true);
        when(springJpaWalletRepository.existsByUserId("user1")).thenReturn(false);

        walletServiceImple.createWallet("user1");

        verify(springJpaWalletRepository, times(1)).existsByUserId("user1");
    }

    @Test
    void testGetWalletBalanceSuccess() {
        Wallet wallet = new Wallet();
        wallet.setUserId("user1");
        wallet.setBalance(100.0);

        when(springJpaWalletRepository.findByUserId("user1")).thenReturn(Optional.of(wallet));

        Double balance = walletServiceImple.getGetWalletBalance("user1");
        assertEquals(100.0, balance);
    }

    @Test
    void testGetWalletBalanceThrowsWhenNotFound() {
        when(springJpaWalletRepository.findByUserId("user1")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> walletServiceImple.getGetWalletBalance("user1"));
    }

    @Test
    void testUpdateWalletBalanceSuccess() {
        Wallet wallet = new Wallet();
        wallet.setUserId("user1");
        wallet.setBalance(50.0);

        when(springJpaWalletRepository.findByUserId("user1")).thenReturn(Optional.of(wallet));
        when(springJpaWalletRepository.save(any(Wallet.class))).thenReturn(wallet);

        walletServiceImple.updateWalletBalance("user1", 25.0);

        assertEquals(75.0, wallet.getBalance());
        verify(springJpaWalletRepository).save(wallet);
    }

    @Test
    void testUpdateWalletBalanceThrowsForNegative() {
        assertThrows(IllegalArgumentException.class, () -> walletServiceImple.updateWalletBalance("user1", -10.0));
    }
}