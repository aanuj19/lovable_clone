package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.memeber.InviteMemberRequest;
import com.aanuj.lovable_clone.dto.memeber.MemberResponse;
import com.aanuj.lovable_clone.dto.memeber.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long userId, UpdateMemberRoleRequest request, Long memberId);

    void removeProjectMember(Long projectId, Long userId, Long memberId);
}
