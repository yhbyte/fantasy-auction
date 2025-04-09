package com.bytes.and.dragons.fantasyauction.util;

import com.bytes.and.dragons.fantasyauction.model.entity.Role;
import com.bytes.and.dragons.fantasyauction.model.request.SignInRequest;
import com.bytes.and.dragons.fantasyauction.model.request.SignUpRequest;

public class TestData {

    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String USER_ROLE = "ROLE_USER";

    private TestData() {
    }

    public static Role getRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");

        return role;
    }

    public static SignUpRequest getSignUpRequest() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername(USER_NAME);
        signUpRequest.setPassword(PASSWORD);
        signUpRequest.setEmail(EMAIL);
        return signUpRequest;
    }

    public static SignInRequest getSignInRequest() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername(USER_NAME);
        signInRequest.setPassword(PASSWORD);
        return signInRequest;
    }
}
