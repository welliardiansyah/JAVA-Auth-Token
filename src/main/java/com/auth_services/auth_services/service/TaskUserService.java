package com.auth_services.auth_services.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.auth_services.auth_services.entity.TaskUserEntity;

public interface TaskUserService {
    public ResponseEntity<Object> createTaskUser(TaskUserEntity taskEntity);
    public ResponseEntity<Object> updateTaskUser(TaskUserEntity taskEntity);
    public ResponseEntity<Object> deleteTaskUser(UUID id);
    Object getTaskUser(UUID id);
    public ResponseEntity<Object> getListting();
}
