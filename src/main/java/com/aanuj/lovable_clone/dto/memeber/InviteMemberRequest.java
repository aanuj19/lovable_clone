package com.aanuj.lovable_clone.dto.memeber;

import com.aanuj.lovable_clone.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
