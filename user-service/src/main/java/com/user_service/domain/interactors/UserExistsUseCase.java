package com.user_service.domain.interactors;

import java.util.UUID;

public interface UserExistsUseCase {

    boolean execute(String email);
}
