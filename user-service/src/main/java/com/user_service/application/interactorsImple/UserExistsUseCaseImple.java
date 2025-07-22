package com.user_service.application.interactorsImple;

import com.user_service.domain.repository.UserRepository;
import com.user_service.domain.interactors.UserExistsUseCase;

public class UserExistsUseCaseImple implements UserExistsUseCase {

    private UserRepository userRepository;

    public UserExistsUseCaseImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
