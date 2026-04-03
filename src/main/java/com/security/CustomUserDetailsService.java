package com.security;



import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo = null;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.entity.User user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                (String) user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}