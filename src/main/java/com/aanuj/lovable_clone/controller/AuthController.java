package com.aanuj.lovable_clone.controller;

import com.aanuj.lovable_clone.dto.auth.AuthResponse;
import com.aanuj.lovable_clone.dto.auth.LoginRequest;
import com.aanuj.lovable_clone.dto.auth.SignUpRequest;
import com.aanuj.lovable_clone.dto.auth.UserProfileResponse;
import com.aanuj.lovable_clone.service.AuthService;
import com.aanuj.lovable_clone.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults (makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthService authService;
    UserService userServie;

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(){
        Long userId = 1L;
        return ResponseEntity.ok(userServie.getProfile(userId));
    }

}
