package com.bytes.and.dragons.fantasyauction.service;

import com.bytes.and.dragons.fantasyauction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String encryptedUsername = encryptionService.encrypt(username);
        return userRepository.findByUsername(encryptedUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}
