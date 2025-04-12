package com.bytes.and.dragons.fantasyauction.service;

import com.bytes.and.dragons.fantasyauction.model.entity.Role;
import com.bytes.and.dragons.fantasyauction.model.entity.User;
import com.bytes.and.dragons.fantasyauction.model.request.SignUpRequest;
import com.bytes.and.dragons.fantasyauction.repository.RoleRepository;
import com.bytes.and.dragons.fantasyauction.repository.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_ROLE = "ROLE_USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long registerUser(SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        Role userRole = roleRepository.findByName(USER_ROLE)
                .orElseGet(() -> roleRepository.save(Role.builder().name(USER_ROLE).build()));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(userRole));

        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            log.error("User not found");
            return new IllegalArgumentException("User not found");
        });
    }

}
