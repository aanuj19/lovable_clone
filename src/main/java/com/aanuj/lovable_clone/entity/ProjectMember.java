package com.aanuj.lovable_clone.entity;

import com.aanuj.lovable_clone.enums.ProjectRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {
    ProjectMemberId id;

    User user;
    ProjectRole role;
    User invitedBy;
    Instant invitedAt;
}
