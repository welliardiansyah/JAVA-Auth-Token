package com.auth_services.auth_services.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.auth_services.auth_services.entity.TaskEntity;

public interface TaskService {
    public ResponseEntity<Object> createTask(TaskEntity taskEntity);
    public ResponseEntity<Object> updateTask(TaskEntity taskEntity);
    public ResponseEntity<Object> deleteTask(UUID id);
    Object getTask(UUID id);
    public ResponseEntity<Object> getListting();
}
