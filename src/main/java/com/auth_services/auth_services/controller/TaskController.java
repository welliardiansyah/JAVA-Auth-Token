package com.auth_services.auth_services.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth_services.auth_services.entity.TaskEntity;
import com.auth_services.auth_services.service.TaskService;

import java.util.UUID;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTask(@RequestBody TaskEntity taskEntity) {
        return taskService.createTask(taskEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@RequestBody TaskEntity taskEntity) {
        return taskService.updateTask(taskEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") UUID id) {
        return taskService.deleteTask(id);
    }

     @GetMapping()
    public ResponseEntity<Object> getLisstingUsers() {
        return taskService.getListting();
    }
    
}
