package com.user_service.domain.interactors;

import com.user_service.application.dto.CreateUserDTO;


public interface RegisterUserUseCase {

    void execute(CreateUserDTO createUserDto);
}
