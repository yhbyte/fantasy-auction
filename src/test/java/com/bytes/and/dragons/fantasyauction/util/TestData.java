package com.bytes.and.dragons.fantasyauction.util;

import com.bytes.and.dragons.fantasyauction.model.dto.ItemDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Role;
import com.bytes.and.dragons.fantasyauction.model.entity.User;
import com.bytes.and.dragons.fantasyauction.model.enums.ItemType;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import com.bytes.and.dragons.fantasyauction.model.request.SignInRequest;
import com.bytes.and.dragons.fantasyauction.model.request.SignUpRequest;
import java.math.BigDecimal;
import java.util.Set;

public class TestData {

    public static final String TEST_NAME = "userName";
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

    public static User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername(TEST_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRoles(Set.of(getRole()));

        return user;
    }

    public static SignUpRequest getSignUpRequest() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername(TEST_NAME);
        signUpRequest.setPassword(PASSWORD);
        signUpRequest.setEmail(EMAIL);
        return signUpRequest;
    }

    public static SignInRequest getSignInRequest() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername(TEST_NAME);
        signInRequest.setPassword(PASSWORD);
        return signInRequest;
    }

    public static CreateLotRequest getCreateLotRequest() {
        CreateLotRequest request = new CreateLotRequest();
        request.setDurationDays(3);
        request.setPrice(BigDecimal.TEN);

        ItemDto itemDto = new ItemDto();
        itemDto.setName(TEST_NAME);
        itemDto.setType(ItemType.OTHER);

        request.setItem(itemDto);
        return request;
    }
}
