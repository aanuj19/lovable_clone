package com.aanuj.lovable_clone.mapper;

import com.aanuj.lovable_clone.dto.project.ProjectResponse;
import com.aanuj.lovable_clone.dto.project.ProjectSummaryResponse;
import com.aanuj.lovable_clone.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    @Mapping(source = "name", target = "projectName")
    ProjectSummaryResponse toListProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListProjectSummaryResponse(List<Project> projects);
}
