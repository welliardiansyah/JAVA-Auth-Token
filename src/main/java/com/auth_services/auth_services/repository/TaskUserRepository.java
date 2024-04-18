package com.auth_services.auth_services.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth_services.auth_services.entity.TaskUserEntity;

public interface TaskUserRepository extends JpaRepository<TaskUserEntity, UUID> {
    Optional<TaskUserEntity> findById(UUID id);
}
