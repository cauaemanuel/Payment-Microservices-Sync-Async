package com.payment_api_service.infrastructure.client;

public class UserClientImple implements com.payment_api_service.domain.client.UserClient {

    private SpringUserClient springUserClient;

    public UserClientImple(SpringUserClient springUserClient) {
        this.springUserClient = springUserClient;
    }

    @Override
    public String emailByToken(String token) {
        return springUserClient.emailByToken(token);
    }
}