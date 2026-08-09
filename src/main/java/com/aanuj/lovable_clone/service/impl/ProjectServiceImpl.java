package com.aanuj.lovable_clone.service.impl;

import com.aanuj.lovable_clone.dto.project.ProjectRequest;
import com.aanuj.lovable_clone.dto.project.ProjectResponse;
import com.aanuj.lovable_clone.dto.project.ProjectSummaryResponse;
import com.aanuj.lovable_clone.entity.Project;
import com.aanuj.lovable_clone.entity.User;
import com.aanuj.lovable_clone.mapper.ProjectMapper;
import com.aanuj.lovable_clone.repository.ProjectRepository;
import com.aanuj.lovable_clone.repository.UserRepository;
import com.aanuj.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.BadRequestException;
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

    @Override
    public List<ProjectSummaryResponse> getUserProject(Long userId) {
        List<Project> projects =  projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(Long userId, ProjectRequest projectRequest) {
        User owner = userRepository.findById(userId).orElseThrow();
        Project project = Project.builder()
                .name(projectRequest.name())
                .owner(owner).isPublic(false).build();
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);

    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You cannot update this project");
        }
        project.setName(projectRequest.name());
        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You cannot delete this project");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAllAccessibleProjectById(projectId,userId).orElseThrow();
    }
}
