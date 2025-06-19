package com.user_service.service;

import com.user_service.config.security.SecurityConfig;
import com.user_service.config.security.service.TokenService;
import com.user_service.model.dto.CreateUserDTO;
import com.user_service.model.dto.LoginUserDto;
import com.user_service.model.dto.RecoveryJwtTokenDto;
import com.user_service.model.entity.User;
import com.user_service.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    private UserRepository userRepository;

    private SecurityConfig securityConfig;

    public UserService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository, SecurityConfig securityConfig) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User userDetails = (User) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(tokenService.generateToken(userDetails));
    }

    public void createUser(CreateUserDTO createUserDto) {

        if (userRepository.findByEmail(createUserDto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado com este email");
        }

        User newUser = new User();
        newUser.setEmail(createUserDto.email());
        newUser.setSenha(securityConfig.passwordEncoder().encode(createUserDto.password()));
        newUser.setNome(createUserDto.nome());
        newUser.setRole(User.UserRole.fromString(createUserDto.role()));
        userRepository.save(newUser);
    }
}
