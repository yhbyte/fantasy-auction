package com.bytes.and.dragons.fantasyauction.security;

import com.bytes.and.dragons.fantasyauction.model.request.SignInRequest;
import com.bytes.and.dragons.fantasyauction.model.request.SignUpRequest;
import com.bytes.and.dragons.fantasyauction.model.response.JwtAuthenticationResponse;
import com.bytes.and.dragons.fantasyauction.service.UserService;
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
        userService.register(request);
        var jwt = jwtService.generateToken(request.getUsername());
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        var jwt = jwtService.generateToken(request.getUsername());
        return new JwtAuthenticationResponse(jwt);
    }

}
