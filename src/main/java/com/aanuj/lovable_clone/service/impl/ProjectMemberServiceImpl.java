package com.aanuj.lovable_clone.service.impl;

import com.aanuj.lovable_clone.dto.memeber.InviteMemberRequest;
import com.aanuj.lovable_clone.dto.memeber.MemberResponse;
import com.aanuj.lovable_clone.dto.memeber.UpdateMemberRoleRequest;
import com.aanuj.lovable_clone.entity.Project;
import com.aanuj.lovable_clone.entity.ProjectMember;
import com.aanuj.lovable_clone.entity.ProjectMemberId;
import com.aanuj.lovable_clone.entity.User;
import com.aanuj.lovable_clone.mapper.ProjectMemberMapper;
import com.aanuj.lovable_clone.repository.ProjectMemberRepository;
import com.aanuj.lovable_clone.repository.ProjectRepository;
import com.aanuj.lovable_clone.repository.UserRepository;
import com.aanuj.lovable_clone.security.AuthUtil;
import com.aanuj.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;
    AuthUtil authUtil;

    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<MemberResponse> getProjectMembers(Long projectId) {
        Project project = getAccessibleProjectById(projectId);
        List<MemberResponse> memberResponseList = projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(projectMemberMapper::toProjectMemberResponseFromMember).toList();

        return memberResponseList;
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Project project = getAccessibleProjectById(projectId);
        User invitee = userRepository.findByUsername(request.username()).orElseThrow();
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if(invitee.getId().equals(authUtil.getCurrentUserId())) {
            throw new RuntimeException("Cannot invite yourself.");
        }
        if(projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Cannot invite once again.");
        }
        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse updateMemberRole(Long projectId,UpdateMemberRoleRequest request, Long memberId) {
        Project project = getAccessibleProjectById(projectId);
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setProjectRole(request.projectRole());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void removeProjectMember(Long projectId, Long memberId) {
        Project project = getAccessibleProjectById(projectId);
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Member not found in project.");
        }
        projectMemberRepository.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return projectRepository.findAllAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
