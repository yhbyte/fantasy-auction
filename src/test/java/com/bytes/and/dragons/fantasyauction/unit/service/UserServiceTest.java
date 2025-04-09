package com.bytes.and.dragons.fantasyauction.unit.service;

import static com.bytes.and.dragons.fantasyauction.util.TestData.EMAIL;
import static com.bytes.and.dragons.fantasyauction.util.TestData.PASSWORD;
import static com.bytes.and.dragons.fantasyauction.util.TestData.USER_NAME;
import static com.bytes.and.dragons.fantasyauction.util.TestData.USER_ROLE;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getRole;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getSignUpRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bytes.and.dragons.fantasyauction.model.entity.User;
import com.bytes.and.dragons.fantasyauction.repository.RoleRepository;
import com.bytes.and.dragons.fantasyauction.repository.UserRepository;
import com.bytes.and.dragons.fantasyauction.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUser_shouldThrowException_whenUsernameAlreadyTaken() {
        // given
        when(userRepository.existsByUsername(any())).thenReturn(true);

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(getSignUpRequest()));
    }

    @Test
    void registerUser_shouldSaveNewUser_whenUsernameAvailable() {
        // given
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(getRole()));
        when(passwordEncoder.encode(any())).thenReturn(PASSWORD);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        // when
        userService.registerUser(getSignUpRequest());

        // then
        verify(roleRepository).findByName(USER_ROLE);
        verify(userRepository).save(userArgumentCaptor.capture());

        assertEquals(USER_NAME, userArgumentCaptor.getValue().getUsername());
        assertEquals(PASSWORD, userArgumentCaptor.getValue().getPassword());
        assertEquals(EMAIL, userArgumentCaptor.getValue().getEmail());
        assertEquals(USER_ROLE, userArgumentCaptor.getValue().getRoles().iterator().next().getName());
    }

}
