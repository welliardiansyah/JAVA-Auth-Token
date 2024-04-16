package com.auth_services.auth_services.repository;

import com.auth_services.auth_services.entity.ERole;
import com.auth_services.auth_services.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(ERole name);
}
