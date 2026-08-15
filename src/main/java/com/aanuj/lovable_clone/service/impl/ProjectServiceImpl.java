package com.aanuj.lovable_clone.service.impl;

import com.aanuj.lovable_clone.dto.project.ProjectRequest;
import com.aanuj.lovable_clone.dto.project.ProjectResponse;
import com.aanuj.lovable_clone.dto.project.ProjectSummaryResponse;
import com.aanuj.lovable_clone.entity.Project;
import com.aanuj.lovable_clone.entity.ProjectMember;
import com.aanuj.lovable_clone.entity.ProjectMemberId;
import com.aanuj.lovable_clone.entity.User;
import com.aanuj.lovable_clone.enums.ProjectRole;
import com.aanuj.lovable_clone.error.ResourceNotFoundException;
import com.aanuj.lovable_clone.mapper.ProjectMapper;
import com.aanuj.lovable_clone.repository.ProjectMemberRepository;
import com.aanuj.lovable_clone.repository.ProjectRepository;
import com.aanuj.lovable_clone.repository.UserRepository;
import com.aanuj.lovable_clone.security.AuthUtil;
import com.aanuj.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    @Override
    public List<ProjectSummaryResponse> getUserProject() {
        Long userId = authUtil.getCurrentUserId();
        List<Project> projects =  projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListProjectSummaryResponse(projects);
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectResponse getUserProjectById(Long projectId) {
        Project project = getAccessibleProjectById(projectId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Long userId = authUtil.getCurrentUserId();
//        User owner = userRepository.findById(userId).orElseThrow(
//                ()-> new ResourceNotFoundException("User", userId.toString())
//        );
        User owner = userRepository.getReferenceById(userId);
        Project project = Project.builder()
                .name(projectRequest.name())
                .isPublic(false).build();
        project = projectRepository.save(project);
        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .invitedAt(Instant.now())
                .acceptedAt(Instant.now())
                .project(project)
                .build();
        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);

    }

    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        Project project = getAccessibleProjectById(id);
        project.setName(projectRequest.name());
        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public void softDelete(Long projectId) {
        Project project = getAccessibleProjectById(projectId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    public Project getAccessibleProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return projectRepository.findAllAccessibleProjectById(projectId,userId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Project", projectId.toString()));
    }
}
