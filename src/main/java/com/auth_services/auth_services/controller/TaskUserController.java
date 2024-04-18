package com.auth_services.auth_services.controller;

import org.springframework.web.bind.annotation.RestController;

import com.auth_services.auth_services.entity.TaskUserEntity;
import com.auth_services.auth_services.service.TaskUserService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/task/users")
public class TaskUserController {
    TaskUserService taskUserService;

    public TaskUserController(TaskUserService taskUserService) {
        this.taskUserService = taskUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTaskJobs(@RequestBody TaskUserEntity taskUserEntity) {
        return taskUserService.createTaskUser(taskUserEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putMethodName(@PathVariable String id, @RequestBody TaskUserEntity taskUserEntity) {
        return taskUserService.updateTaskUser(taskUserEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") UUID id) {
        return taskUserService.deleteTaskUser(id);
    }

    @GetMapping()
    public ResponseEntity<Object> getLisstingUsers() {
        return taskUserService.getListting();
    }
    
}
