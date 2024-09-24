package com.example.account.controllers;

import com.example.account.dtos.RegisterUserDto;
import com.example.account.entities.User;
import com.example.account.exceptions.EmailAlreadyExistsException;
import com.example.account.exceptions.UserErrorException;
import com.example.account.services.AuthenticationService;
import com.example.account.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody RegisterUserDto registerUserDto) {

        try {
            User registeredUser = authenticationService.signup(registerUserDto);

            ApiResponse<User> response = new ApiResponse<>(true, "User registered successfully", registeredUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EmailAlreadyExistsException e) {
            ApiResponse<User> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
