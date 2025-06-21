package com.user_service.controller;

import com.user_service.model.dto.CreateUserDTO;
import com.user_service.model.dto.LoginUserDto;
import com.user_service.model.dto.RecoveryJwtTokenDto;
import com.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Autentica um usuário e retorna o token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(summary = "Registra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário já existe")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDto) {
        userService.registerUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Verifica se um usuário existe pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna true se o usuário existe, false caso contrário")
    })
    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(@RequestParam String userId) {
        boolean exists = userService.isUserExists(userId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

}