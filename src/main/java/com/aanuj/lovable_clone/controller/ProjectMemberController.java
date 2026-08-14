package com.aanuj.lovable_clone.controller;

import com.aanuj.lovable_clone.dto.memeber.InviteMemberRequest;
import com.aanuj.lovable_clone.dto.memeber.MemberResponse;
import com.aanuj.lovable_clone.dto.memeber.UpdateMemberRoleRequest;
import com.aanuj.lovable_clone.entity.ProjectMember;
import com.aanuj.lovable_clone.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(@PathVariable Long projectId){
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(@PathVariable Long projectId, @RequestBody @Valid InviteMemberRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId, request)
        );
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(@PathVariable Long memberId, @PathVariable Long projectId, @RequestBody @Valid UpdateMemberRoleRequest request){
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId,request, memberId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long memberId, @PathVariable Long projectId){
        projectMemberService.removeProjectMember(projectId,memberId);
        return ResponseEntity.noContent().build();
    }
}
