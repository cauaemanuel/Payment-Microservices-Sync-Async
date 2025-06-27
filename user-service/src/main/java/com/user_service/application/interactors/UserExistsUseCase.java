package com.user_service.application.interactors;

import java.util.UUID;

public interface UserExistsUseCase {

    boolean execute(UUID userId);
}
