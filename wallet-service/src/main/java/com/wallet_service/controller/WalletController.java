package com.wallet_service.controller;

import com.wallet_service.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/{id}")
    public ResponseEntity createWallet(@PathVariable String id) {
        walletService.createWallet(id);
        return ResponseEntity.ok("Wallet created successfully");
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getWalletBalance(@PathVariable String id) {
        Double balance = walletService.getGetWalletBalance(id);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/{id}/update-balance")
    public ResponseEntity updateWalletBalance(@PathVariable String id, @RequestParam Double newBalance) {
        walletService.updateWalletBalance(id, newBalance);
        return ResponseEntity.ok("Wallet balance updated successfully");
    }

}
