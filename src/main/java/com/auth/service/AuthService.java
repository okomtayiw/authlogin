package com.auth.service;

import com.auth.model.AuthResponse;
import com.auth.model.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
}
