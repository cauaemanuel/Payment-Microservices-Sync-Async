package com.user_service.service;

import com.user_service.config.security.SecurityConfig;
import com.user_service.config.security.service.TokenService;
import com.user_service.model.dto.CreateUserDTO;
import com.user_service.model.dto.LoginUserDto;
import com.user_service.model.dto.RecoveryJwtTokenDto;
import com.user_service.repository.UserRepository;
import com.user_service.service.interactors.LoginUserUseCase;
import com.user_service.service.interactors.RegisterUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {

    private LoginUserUseCase loginUserUseCase;
    private RegisterUserUseCase registerUserUseCase;
    private UserRepository userRepository;

    public UserService(
            AuthenticationManager authenticationManager,
            TokenService tokenService,
            UserRepository userRepository,
            SecurityConfig securityConfig) {
        this.loginUserUseCase = new LoginUserUseCase(authenticationManager, tokenService);
        this.registerUserUseCase = new RegisterUserUseCase(userRepository, securityConfig);
        this.userRepository = userRepository;
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        return loginUserUseCase.loginUser(loginUserDto);
    }

    public void registerUser(CreateUserDTO createUserDto) {
        registerUserUseCase.registerUser(createUserDto);
    }

    public boolean isUserExists(String userId){
        if (userId == null || userId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID cannot be null or empty");
        }
        return userRepository.findById(UUID.fromString(userId)).isPresent();
    }

}
