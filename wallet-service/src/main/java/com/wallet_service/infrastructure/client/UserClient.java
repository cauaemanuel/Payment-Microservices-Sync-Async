package com.wallet_service.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/users/exists")
    Boolean exists(@RequestParam("userId") UUID userId);
}