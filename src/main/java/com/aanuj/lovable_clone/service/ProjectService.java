package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.project.ProjectRequest;
import com.aanuj.lovable_clone.dto.project.ProjectResponse;
import com.aanuj.lovable_clone.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProject();
    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest projectRequest);

    ProjectResponse updateProject(Long id, ProjectRequest projectRequest);

    void softDelete(Long id);
}
