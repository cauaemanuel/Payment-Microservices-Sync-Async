package com.user_service.domain.interactors;

public interface GetEmailByTokenUseCase {

    String execute(String token);
}
