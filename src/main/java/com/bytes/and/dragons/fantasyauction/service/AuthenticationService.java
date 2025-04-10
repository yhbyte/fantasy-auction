package com.bytes.and.dragons.fantasyauction.service;

import com.bytes.and.dragons.fantasyauction.model.request.SignInRequest;
import com.bytes.and.dragons.fantasyauction.model.request.SignUpRequest;
import com.bytes.and.dragons.fantasyauction.model.response.JwtAuthenticationResponse;
import com.bytes.and.dragons.fantasyauction.security.CustomUserDetails;
import com.bytes.and.dragons.fantasyauction.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        Long userId = userService.registerUser(request);
        var jwt = jwtService.generateToken(request.getUsername(), userId);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        Long userId = ((CustomUserDetails) auth.getPrincipal()).getId();
        var jwt = jwtService.generateToken(request.getUsername(), userId);
        return new JwtAuthenticationResponse(jwt);
    }

}
