package com.aanuj.lovable_clone.controller;

import com.aanuj.lovable_clone.dto.project.FileContetResponse;
import com.aanuj.lovable_clone.dto.project.FileNode;
import com.aanuj.lovable_clone.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project/{id}")
public class FileController {
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileNode>> getFileTree(@PathVariable Long projectId){
        Long userId = 1L;
        return ResponseEntity.ok(fileService.getFileTree(userId, projectId));
    }

    @GetMapping("/{*path}")
    public ResponseEntity<FileContetResponse> getFile(@PathVariable Long projectId, @PathVariable String path){
        Long userId = 1L;
        return ResponseEntity.ok(fileService.getFileContent(projectId, path, userId));
    }

}
