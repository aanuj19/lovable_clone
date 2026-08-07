package com.aanuj.lovable_clone.service.impl;

import com.aanuj.lovable_clone.dto.project.FileContetResponse;
import com.aanuj.lovable_clone.dto.project.FileNode;
import com.aanuj.lovable_clone.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long userId, Long projectId) {
        return List.of();
    }

    @Override
    public FileContetResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
