package com.user_service.service;

import com.user_service.infrastructure.security.SecurityConfig;
import com.user_service.infrastructure.security.TokenService;
import com.user_service.domain.dto.CreateUserDTO;
import com.user_service.domain.dto.LoginUserDto;
import com.user_service.domain.dto.RecoveryJwtTokenDto;
import com.user_service.domain.entity.User;
import com.user_service.infrastructure.persistence.SpringDataUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;
    @Mock
    private SpringDataUserRepository springDataUserRepository;
    @Mock
    private SecurityConfig securityConfig;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        lenient().when(securityConfig.passwordEncoder()).thenReturn(passwordEncoder);
    }

    @Test
    void deveAutenticarUsuarioComSucesso() {
        LoginUserDto loginDto = new LoginUserDto("email@teste.com", "senha");
        User user = new User();
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(tokenService.generateToken(user)).thenReturn("token-jwt");

        RecoveryJwtTokenDto result = userService.authenticateUser(loginDto);

        assertEquals("token-jwt", result.token());
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        CreateUserDTO dto = new CreateUserDTO("nome", "email@teste.com", "senha", "CUSTOMER");
        when(springDataUserRepository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(dto.password())).thenReturn("senha-criptografada");

        assertDoesNotThrow(() -> userService.registerUser(dto));
        verify(springDataUserRepository, times(1)).save(any(User.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExisteNoCadastro() {
        CreateUserDTO dto = new CreateUserDTO("nome", "email@teste.com", "senha", "CUSTOMER");
        when(springDataUserRepository.findByEmail(dto.email())).thenReturn(Optional.of(new User()));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> userService.registerUser(dto));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        assertEquals("Usuário já cadastrado com este email", ex.getReason());
    }
}