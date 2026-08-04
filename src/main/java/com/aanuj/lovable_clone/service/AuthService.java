package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.auth.AuthResponse;
import com.aanuj.lovable_clone.dto.auth.LoginRequest;
import com.aanuj.lovable_clone.dto.auth.SignUpRequest;

public interface AuthService {
    AuthResponse signUp(SignUpRequest request);
    AuthResponse login(LoginRequest request);
}
