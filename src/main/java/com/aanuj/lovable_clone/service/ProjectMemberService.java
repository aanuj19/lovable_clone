package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.memeber.InviteMemberRequest;
import com.aanuj.lovable_clone.dto.memeber.MemberResponse;
import com.aanuj.lovable_clone.entity.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMember> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long userId, InviteMemberRequest request, Long memberId);

    MemberResponse deleteProjectMember(Long projectId, Long userId, Long memberId);
}
