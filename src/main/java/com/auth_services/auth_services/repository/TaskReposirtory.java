package com.auth_services.auth_services.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth_services.auth_services.entity.TaskEntity;



public interface TaskReposirtory extends JpaRepository<TaskEntity, UUID> {
    Optional<TaskEntity> findById(UUID id);
}
