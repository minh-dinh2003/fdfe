package com.tmss.backend.auth;

import com.tmss.backend.config.JwtService;
import com.tmss.backend.entity.Account;
import com.tmss.backend.entity.Role;
import com.tmss.backend.repositories.AccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        // Check if the username or email already exists
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadCredentialsException("Username is already in use");
        }

        // Create and save the account
        var account = Account.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .bankAccount(request.getBankAccount())
                .active(request.getActive())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(account);

        // Generate JWT token for the registered user
        var jwtToken = jwtService.generateToken(account, Collections.singletonList(account.getRole().name()));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Find the authenticated account
        var account = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token
        var jwtToken = jwtService.generateToken(account, Collections.singletonList(account.getRole().name()));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
