// src/test/java/com/user_service/controllers/UserControllerTest.java
package com.user_service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_service.application.dto.LoginUserDto;
import com.user_service.application.dto.RecoveryJwtTokenDto;
import com.user_service.application.interactors.LoginUserUseCase;
import com.user_service.application.interactors.RegisterUserUseCase;
import com.user_service.application.interactors.UserExistsUseCase;
import com.user_service.infrastructure.persistence.SpringDataUserRepository;
import com.user_service.infrastructure.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private LoginUserUseCase loginUserUseCase;

    @MockitoBean
    private RegisterUserUseCase registerUserUseCase;

    @MockitoBean
    private UserExistsUseCase userExistsUseCase;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Nested
    class LoginUserTest {

        @Test
        void deveRetornarTokenQuandoLoginValido() throws Exception {
            LoginUserDto loginDto = new LoginUserDto("validUser", "validPassword");
            RecoveryJwtTokenDto tokenDto = new RecoveryJwtTokenDto("validToken");

            Mockito.when(loginUserUseCase.execute(any(LoginUserDto.class))).thenReturn(tokenDto);

            mockMvc.perform(post("/users/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(loginDto)))
                    .andExpect(status().isOk());
        }

        @Test
        void deveRetornarUnauthorizedQuandoLoginInvalido() throws Exception {
            LoginUserDto loginDto = new LoginUserDto("invalidUser", "invalidPassword");


            Mockito.when(loginUserUseCase.execute(any(LoginUserDto.class)))
                    .thenThrow(new BadCredentialsException("Credenciais inválidas"));

            mockMvc.perform(post("/users/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(loginDto)))
                    .andExpect(status().isUnauthorized());
        }
    }
}