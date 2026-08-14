package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.memeber.InviteMemberRequest;
import com.aanuj.lovable_clone.dto.memeber.MemberResponse;
import com.aanuj.lovable_clone.dto.memeber.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, UpdateMemberRoleRequest request, Long memberId);

    void removeProjectMember(Long projectId, Long memberId);
}
