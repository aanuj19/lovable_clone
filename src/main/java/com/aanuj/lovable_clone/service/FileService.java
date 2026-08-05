package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.project.FileContetResponse;
import com.aanuj.lovable_clone.dto.project.FileNode;

import java.util.List;

public interface FileService {

    List<FileNode> getFileTree(Long userId, Long projectId);

    FileContetResponse getFileContent(Long projectId, String path, Long userId);
}
