package com.wallet_service.infrastructure.controller;

import com.wallet_service.application.interactors.CreateWalletUseCase;
import com.wallet_service.domain.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@Slf4j
public class WalletController {

    private final CreateWalletUseCase createWalletUseCase;
    private final WalletService walletService;

    public WalletController(CreateWalletUseCase createWalletUseCase, WalletService walletService) {
        this.createWalletUseCase = createWalletUseCase;
        this.walletService = walletService;
    }

    @PostMapping("/create")
    public ResponseEntity createWallet(@RequestHeader("Authorization") String authorization) {
        String token = extractToken(authorization);
        log.info("Creating wallet for token: {}", token);
        createWalletUseCase.createWallet(token);
        return ResponseEntity.ok("Wallet created successfully");
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getWalletBalance(@RequestHeader("Authorization") String authorization) {
        String token = extractToken(authorization);
        Double balance = walletService.getGetWalletBalance(token);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/update-balance")
    public ResponseEntity updateWalletBalance(
            @RequestHeader("Authorization") String authorization,
            @RequestParam Double newBalance) {
        String token = extractToken(authorization);
        walletService.updateWalletBalance(token, newBalance);
        return ResponseEntity.ok("Wallet balance updated successfully");
    }

    @GetMapping("/verify-amount")
    public ResponseEntity verifyAmount(
            @RequestParam String email,
            @RequestParam Double amount) {
        boolean isValid = walletService.verifyAmount(email, amount);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(@RequestParam String email) {
        boolean exists = walletService.isWalletExists(email);
        return ResponseEntity.ok(exists);
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return authorizationHeader;
    }
}