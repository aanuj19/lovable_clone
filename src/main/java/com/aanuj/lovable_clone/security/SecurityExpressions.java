package com.aanuj.lovable_clone.security;

import com.aanuj.lovable_clone.enums.ProjectPermission;
import com.aanuj.lovable_clone.enums.ProjectRole;
import com.aanuj.lovable_clone.repository.ProjectMemberRepository;
import com.aanuj.lovable_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpressions {
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public boolean hasPermission(Long projectId, ProjectPermission permission) {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId)
                .map(role -> role.getPermissions().contains(permission))
                .orElse(false);
    }

    public boolean canViewProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return hasPermission(projectId, ProjectPermission.VIEW);
    }

    public boolean canEditProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return hasPermission(projectId, ProjectPermission.EDIT);
    }

    public boolean canDeleteProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return hasPermission(projectId, ProjectPermission.DELETE);
    }

    public boolean canViewMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return hasPermission(projectId, ProjectPermission.VIEW_MEMBERS);
    }
    public boolean canManageMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return hasPermission(projectId, ProjectPermission.MANAGE_MEMBERS);
    }
}
