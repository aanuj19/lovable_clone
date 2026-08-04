package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
