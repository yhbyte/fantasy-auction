package com.bytes.and.dragons.fantasyauction.unit.service;

import static com.bytes.and.dragons.fantasyauction.util.TestData.getSignInRequest;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getSignUpRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bytes.and.dragons.fantasyauction.security.JwtService;
import com.bytes.and.dragons.fantasyauction.service.AuthenticationService;
import com.bytes.and.dragons.fantasyauction.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void signUp_shouldRegisterUser_whenInvoked() {
        // given
        when(jwtService.generateToken(any())).thenReturn("token");

        // when
        var response = authenticationService.signUp(getSignUpRequest());

        // then
        verify(userService).registerUser(any());
        assertEquals("token", response.getToken());
    }

    @Test
    void signIn_shouldAuthenticateUser_whenInvoked() {
        // given
        when(jwtService.generateToken(any())).thenReturn("token");

        // when
        var response = authenticationService.signIn(getSignInRequest());

        // then
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertEquals("token", response.getToken());
    }
}
