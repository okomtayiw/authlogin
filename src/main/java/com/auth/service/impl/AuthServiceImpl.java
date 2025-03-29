package com.auth.service.impl;

import com.auth.entity.Users;
import com.auth.model.AuthResponse;
import com.auth.model.LoginRequest;
import com.auth.repository.UserRepository;
import com.auth.service.AuthService;
import com.auth.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Optional<Users> user = userRepository.findByIdentifier(request.getIdentifier());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.get());
        return new AuthResponse(token);
    }
}
