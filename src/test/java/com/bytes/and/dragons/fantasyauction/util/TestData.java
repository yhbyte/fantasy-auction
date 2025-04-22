package com.bytes.and.dragons.fantasyauction.util;

import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import com.bytes.and.dragons.fantasyauction.model.entity.Role;
import com.bytes.and.dragons.fantasyauction.model.entity.User;
import com.bytes.and.dragons.fantasyauction.model.enums.ItemType;
import com.bytes.and.dragons.fantasyauction.model.request.CreateItemRequest;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import com.bytes.and.dragons.fantasyauction.model.request.SignInRequest;
import com.bytes.and.dragons.fantasyauction.model.request.SignUpRequest;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public class TestData {

    public static final String TEST_NAME = "userName";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String USER_ROLE = "ROLE_USER";
    public static final Long TEST_ID = 1L;

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
        request.setEndTime(Instant.now().plusSeconds(360000));
        request.setPrice(BigDecimal.TEN);
        request.setItemId(TEST_ID);
        return request;
    }

    public static CreateItemRequest getCreateItemRequest() {
        CreateItemRequest request = new CreateItemRequest();
        request.setName(TEST_NAME);
        request.setType(ItemType.OTHER);
        return request;
    }

    public static Item getItem() {
        Item item = new Item();
        item.setId(TEST_ID);
        item.setName(TEST_NAME);
        item.setType(ItemType.OTHER);
        return item;
    }

    public static Lot getLot() {
        Lot lot = new Lot();
        lot.setId(TEST_ID);
        lot.setSeller(getUser());
        lot.setItem(getItem());

        return lot;
    }
}
