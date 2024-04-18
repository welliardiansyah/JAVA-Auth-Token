package com.auth_services.auth_services.repository;

import com.auth_services.auth_services.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
    Optional<UserEntity> findID(UUID id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
