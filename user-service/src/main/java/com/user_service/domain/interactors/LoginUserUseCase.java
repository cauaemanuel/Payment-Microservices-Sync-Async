package com.user_service.domain.interactors;

import com.user_service.application.dto.LoginUserDto;
import com.user_service.application.dto.RecoveryJwtTokenDto;

public interface LoginUserUseCase {

    RecoveryJwtTokenDto execute(LoginUserDto loginUserDto);

}
