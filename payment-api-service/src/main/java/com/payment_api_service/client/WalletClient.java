package com.payment_api_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wallet-service", url = "${wallet.service.url}")
public interface WalletClient {

    @GetMapping("/wallet/{id}/verify-amount")
    boolean verifyAmount(@PathVariable String id, @RequestParam Double amount);
}
