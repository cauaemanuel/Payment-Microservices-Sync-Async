package com.wallet_service.infrastructure.controller;

import com.wallet_service.application.interactors.CreateWalletUseCase;
import com.wallet_service.domain.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Wallet", description = "Operações de carteira")
@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final CreateWalletUseCase createWalletUseCase;
    private final WalletService walletService;

    public WalletController(CreateWalletUseCase createWalletUseCase, WalletService walletService) {
        this.createWalletUseCase = createWalletUseCase;
        this.walletService = walletService;
    }

    @Operation(summary = "Cria uma carteira para o usuário")
    @PostMapping("/{id}")
    public ResponseEntity createWallet(
            @Parameter(description = "ID do usuário") @PathVariable String id) {
        createWalletUseCase.createWallet(id);
        return ResponseEntity.ok("Wallet created successfully");
    }

    @Operation(summary = "Consulta o saldo da carteira")
    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getWalletBalance(
            @Parameter(description = "ID do usuário") @PathVariable String id) {
        Double balance = walletService.getGetWalletBalance(id);
        return ResponseEntity.ok(balance);
    }

    @Operation(summary = "Atualiza o saldo da carteira")
    @PostMapping("/{id}/update-balance")
    public ResponseEntity updateWalletBalance(
            @Parameter(description = "ID do usuário") @PathVariable String id,
            @Parameter(description = "Novo saldo") @RequestParam Double newBalance) {
        walletService.updateWalletBalance(id, newBalance);
        return ResponseEntity.ok("Wallet balance updated successfully");
    }

    @Operation(summary = "Verifica se o valor é válido para a carteira")
    @GetMapping("/{id}/verify-amount")
    public ResponseEntity verifyAmount(
            @Parameter(description = "ID do usuário") @PathVariable String id,
            @Parameter(description = "Valor a verificar") @RequestParam Double amount) {
        boolean isValid = walletService.verifyAmount(id, amount);
        return ResponseEntity.ok(isValid);
    }

    @Operation(summary = "Verifica se a carteira existe")
    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(
            @Parameter(description = "ID do usuário") @RequestParam String userId) {
        boolean exists = walletService.isWalletExists(userId);
        return ResponseEntity.ok(exists);
    }
}