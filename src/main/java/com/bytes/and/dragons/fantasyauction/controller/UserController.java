package com.bytes.and.dragons.fantasyauction.controller;

import com.bytes.and.dragons.fantasyauction.model.request.RegisterRequest;
import com.bytes.and.dragons.fantasyauction.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(Authentication authentication) {
        return ResponseEntity.ok("Hello, " + authentication.getName());
    }

}