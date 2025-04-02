package com.bytes.and.dragons.fantasyauction.service;

import com.bytes.and.dragons.fantasyauction.model.Role;
import com.bytes.and.dragons.fantasyauction.model.User;
import com.bytes.and.dragons.fantasyauction.repository.RoleRepository;
import com.bytes.and.dragons.fantasyauction.repository.UserRepository;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final EncryptionService encryptionService;

    public User registerUser(String username, String email, String rawPassword) {

        String encryptedUsername = encryptionService.encrypt(username);
        Optional<User> existingUser = userRepository.findByUsername(encryptedUsername);
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));

        Role roleUser = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role ROLE_USER not found"));
        user.setRoles(Collections.singleton(roleUser));

        return userRepository.save(user);
    }
}
