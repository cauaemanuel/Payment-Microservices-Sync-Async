package com.user_service.infrastructure.config;

import com.user_service.application.interactors.GetEmailByTokenUseCase;
import com.user_service.application.interactorsImple.GetEmailByTokenUseCaseImple;
import com.user_service.infrastructure.persistence.UserRepositoryImple;
import com.user_service.application.interactors.LoginUserUseCase;
import com.user_service.application.interactors.RegisterUserUseCase;
import com.user_service.application.interactors.UserExistsUseCase;
import com.user_service.application.interactorsImple.LoginUserUseCaseImple;
import com.user_service.application.interactorsImple.RegisterUserUseCaseImple;
import com.user_service.application.interactorsImple.UserExistsUseCaseImple;
import com.user_service.domain.repository.UserRepository;
import com.user_service.infrastructure.persistence.SpringDataUserRepository;
import com.user_service.infrastructure.security.SecurityConfig;
import com.user_service.infrastructure.security.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class BeanConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository, SecurityConfig securityConfig) {
        return new RegisterUserUseCaseImple(userRepository, securityConfig);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(AuthenticationManager authenticationManager, TokenService tokenService) {
        return new LoginUserUseCaseImple(authenticationManager, tokenService);
    }

    @Bean
    public UserExistsUseCase userExistsUseCase(UserRepository userRepository) {
        return new UserExistsUseCaseImple(userRepository);
    }

    @Bean
    public UserRepository userRepository(SpringDataUserRepository springDataUserRepository) {
        return new UserRepositoryImple(springDataUserRepository);
    }

    @Bean
    public GetEmailByTokenUseCase getEmailByTokenUseCase(TokenService tokenService) {
        return new GetEmailByTokenUseCaseImple(tokenService);
    }

}