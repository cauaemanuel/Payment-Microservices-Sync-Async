package com.user_service.service.interactors;

import com.user_service.config.security.SecurityConfig;
import com.user_service.model.dto.CreateUserDTO;
import com.user_service.model.entity.User;
import com.user_service.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RegisterUserUseCase {

    private UserRepository userRepository;
    private SecurityConfig securityConfig;

    public RegisterUserUseCase(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public void registerUser(CreateUserDTO createUserDto) {

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
