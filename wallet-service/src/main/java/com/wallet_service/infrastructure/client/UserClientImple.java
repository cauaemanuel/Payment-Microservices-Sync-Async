package com.wallet_service.infrastructure.client;

import com.wallet_service.domain.client.UserClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserClientImple implements UserClient {

    private SpringUserClient springUserClient;

    public UserClientImple(SpringUserClient springUserClient) {
        this.springUserClient = springUserClient;
    }

    @Override
    public Boolean exists(String userEmail) {
        return springUserClient.exists(userEmail);
    }

    @Override
    public String emailByToken(String token) {
        return springUserClient.emailByToken(token);
    }

}
