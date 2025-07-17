package com.wallet_service.domain.client;

public interface UserClient {

    Boolean exists(String userEmail);

    String emailByToken(String token);
}
