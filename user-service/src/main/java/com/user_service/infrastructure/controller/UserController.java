package com.user_service.infrastructure.controller;

import com.user_service.application.interactors.GetEmailByTokenUseCase;
import com.user_service.application.interactors.LoginUserUseCase;
import com.user_service.application.interactors.RegisterUserUseCase;
import com.user_service.application.interactors.UserExistsUseCase;
import com.user_service.application.dto.CreateUserDTO;
import com.user_service.application.dto.LoginUserDto;
import com.user_service.application.dto.RecoveryJwtTokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private LoginUserUseCase loginUserUseCase;
    private RegisterUserUseCase registerUserUseCase;
    private UserExistsUseCase userExistsUseCase;
    private GetEmailByTokenUseCase getEmailByTokenUseCase;

    public UserController(
            LoginUserUseCase loginUserUseCase,
            RegisterUserUseCase registerUserUseCase,
            UserExistsUseCase userExistsUseCase,
            GetEmailByTokenUseCase getEmailByTokenUseCase) {
        this.loginUserUseCase = loginUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.userExistsUseCase = userExistsUseCase;
        this.getEmailByTokenUseCase = getEmailByTokenUseCase;
    }

    @Operation(summary = "Autentica um usuário e retorna o token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> login(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = loginUserUseCase.execute(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(summary = "Registra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário já existe")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateUserDTO createUserDto) {
        registerUserUseCase.execute(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Verifica se um usuário existe pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna true se o usuário existe, false caso contrário")
    })
    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(@RequestParam String userEmail) {
        boolean exists = userExistsUseCase.execute(userEmail);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/email-by-token")
    public ResponseEntity<String> getEmailByToken(@RequestParam String token) {
        var email = getEmailByTokenUseCase.execute(token);
        return ResponseEntity.ok(email);
    }

}