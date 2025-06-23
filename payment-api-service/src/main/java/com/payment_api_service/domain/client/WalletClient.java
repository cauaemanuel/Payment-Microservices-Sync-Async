package com.payment_api_service.domain.client;


public interface WalletClient {

    boolean exists(String userId);
    boolean verifyAmount( String id, Double amount);
}
