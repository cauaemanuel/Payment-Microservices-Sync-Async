package com.payment_api_service.infrastructure.client;

import com.payment_api_service.domain.client.WalletClient;

public class WalletClientImple implements WalletClient {

    private SpringWalletClient springWalletClient;

    public WalletClientImple(SpringWalletClient springWalletClient) {
        this.springWalletClient = springWalletClient;
    }

    @Override
    public boolean exists(String userId) {
        return springWalletClient.exists(userId);
    }

    @Override
    public boolean verifyAmount(String id, Double amount) {
        return springWalletClient.verifyAmount(id, amount);
    }
}
