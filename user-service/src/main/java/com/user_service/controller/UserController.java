package com.user_service.controller;

import com.user_service.model.dto.CreateUserDTO;
import com.user_service.model.dto.LoginUserDto;
import com.user_service.model.dto.RecoveryJwtTokenDto;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDto) {
        userService.registerUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(@RequestParam String userId) {
        boolean exists = userService.isUserExists(userId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

}