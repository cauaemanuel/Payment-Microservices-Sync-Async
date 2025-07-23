package com.payment_api_service.domain.client;

public interface UserClient {

    String emailByToken(String token);
}
