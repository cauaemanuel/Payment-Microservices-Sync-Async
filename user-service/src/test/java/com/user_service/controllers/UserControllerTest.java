package com.user_service.controllers;


import com.user_service.application.interactors.LoginUserUseCase;
import com.user_service.application.interactors.RegisterUserUseCase;
import com.user_service.application.interactors.UserExistsUseCase;
import com.user_service.infrastructure.controller.UserController;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LoginUserUseCase loginUserUseCase;

    @Mock
    private RegisterUserUseCase registerUserUseCase;

    @Mock
    private UserExistsUseCase userExistsUseCase;

    class LoginUserTest {

    }
}
