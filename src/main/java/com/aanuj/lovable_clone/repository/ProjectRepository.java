package com.aanuj.lovable_clone.repository;

import com.aanuj.lovable_clone.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("""
        SELECT p from Project p WHERE p.deletedAt is NULL and p.owner.id =:userId
                ORDER BY p.updatedAt DESC
        """)
    List<Project> findAllAccessibleByUser(@Param("userId") Long userId);

    @Query("""
        SELECT p from Project p 
        LEFT JOIN FETCH p.owner 
            WHERE p.id =:projectId AND p.owner.id=:userId AND p.deletedAt IS NULL
        """)
    Optional<Project> findAllAccessibleProjectById(@Param("projectId") Long projectId ,@Param("userId") Long userId);
}
