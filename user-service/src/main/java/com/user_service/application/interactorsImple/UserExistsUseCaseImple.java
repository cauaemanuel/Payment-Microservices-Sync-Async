package com.user_service.application.interactorsImple;

import com.user_service.domain.repository.UserRepository;
import com.user_service.application.interactors.UserExistsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class UserExistsUseCaseImple implements UserExistsUseCase {

    private UserRepository userRepository;

    public UserExistsUseCaseImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(UUID userId) {
        return userRepository.findById(userId).isPresent();
    }
}
