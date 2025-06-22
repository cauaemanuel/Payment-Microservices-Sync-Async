package com.user_service.application.interactors;

import com.user_service.domain.dto.LoginUserDto;
import com.user_service.domain.dto.RecoveryJwtTokenDto;

public interface LoginUserUseCase {

    RecoveryJwtTokenDto execute(LoginUserDto loginUserDto);

}
