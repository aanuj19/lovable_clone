package com.aanuj.lovable_clone.dto.memeber;

import com.aanuj.lovable_clone.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole projectRole
) {
}
