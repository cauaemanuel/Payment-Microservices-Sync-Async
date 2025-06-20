package com.user_service.service.interactors;

import com.user_service.config.security.service.TokenService;
import com.user_service.model.dto.LoginUserDto;
import com.user_service.model.dto.RecoveryJwtTokenDto;
import com.user_service.model.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class LoginUserUseCase {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public LoginUserUseCase(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public RecoveryJwtTokenDto loginUser(LoginUserDto loginUserDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User userDetails = (User) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(tokenService.generateToken(userDetails));
    }
}
