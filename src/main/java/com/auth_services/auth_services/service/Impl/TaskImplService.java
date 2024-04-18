package com.auth_services.auth_services.service.Impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auth_services.auth_services.entity.TaskEntity;
import com.auth_services.auth_services.exception.NotFoundException;
import com.auth_services.auth_services.repository.TaskReposirtory;
import com.auth_services.auth_services.response.ResponseHandler;
import com.auth_services.auth_services.service.TaskService;

@Service
public class TaskImplService implements TaskService {
    TaskReposirtory taskReposirtory;

    @Autowired
    public TaskImplService(TaskReposirtory taskReposirtory) {
        this.taskReposirtory = taskReposirtory;
    }

    @Override
    public ResponseEntity<Object> createTask(TaskEntity taskEntity) {
        if (taskEntity.getAuthor() == null) {
            return ResponseHandler.errorResponseBuilder(
                "Authcor cannot be null.",
                HttpStatus.BAD_REQUEST
            );
        }

        try {
            TaskEntity createTask = taskReposirtory.save(taskEntity);
            if (createTask != null) {
                Map<String, Object> taskInfo = new LinkedHashMap<>();
                taskInfo.put("id", createTask.getId());
                taskInfo.put("name", createTask.getName());
                taskInfo.put("authcor", createTask.getAuthor());
                taskInfo.put("description", createTask.getDescription());
                taskInfo.put("created_at", createTask.getCreatedAt());
                taskInfo.put("updated_at", createTask.getUpdatedAt());

                return ResponseHandler.successResponseBuilder(
                        "Task created successfully!",
                        HttpStatus.OK,
                        taskInfo
                );
            } else {
                return ResponseHandler.errorResponseBuilder(
                        "Task creation failed.",
                        HttpStatus.BAD_REQUEST
                );
            }
        } catch (IllegalArgumentException e) {
            return ResponseHandler.errorResponseBuilder(
                    "Invalid UUID format for authcor.",
                    HttpStatus.BAD_REQUEST
            );
        } catch (DataAccessException e) {
            throw new NotFoundException("Task creation failed: " + e.getMessage());
        }
    }


    @Override
    public ResponseEntity<Object> updateTask(TaskEntity taskEntity) {
        if (!taskReposirtory.existsById(taskEntity.getId())) {
            return ResponseHandler.errorResponseBuilder(
                    "Task anda tidak dapat ditemuakn!.",
                    HttpStatus.NOT_FOUND
            );
        }
        try {
            TaskEntity createTask = taskReposirtory.save(taskEntity);
            if (createTask != null) {
                Map<String, Object> taskInfo = new LinkedHashMap<>();
                taskInfo.put("id", createTask.getId());
                taskInfo.put("name", createTask.getName());
                taskInfo.put("author", createTask.getAuthor());
                taskInfo.put("description", createTask.getDescription());
                taskInfo.put("created_at", createTask.getCreatedAt());
                taskInfo.put("updated_at", createTask.getUpdatedAt());

                return ResponseHandler.successResponseBuilder(
                        "Task updated successfully!",
                        HttpStatus.OK,
                        taskInfo
                );
            } else {
                return ResponseHandler.errorResponseBuilder(
                        "Task updated failed.",
                        HttpStatus.BAD_REQUEST
                );
            }
        } catch (DataAccessException e) {
            throw new NotFoundException("Create task cannot be successfully!" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> deleteTask(UUID id) {
        Optional<TaskEntity> existingUser = taskReposirtory.findById(id);
        TaskEntity user = existingUser.get();

        Map<String, Object> taskInfo = new LinkedHashMap<>();
                taskInfo.put("id", user.getId());
                taskInfo.put("name", user.getName());
                taskInfo.put("author", user.getAuthor());
                taskInfo.put("description", user.getDescription());
                taskInfo.put("created_at", user.getCreatedAt());
                taskInfo.put("updated_at", user.getUpdatedAt());
        try {
            if (existingUser.isPresent()) {
                taskReposirtory.delete(user); 
                return ResponseHandler.successResponseBuilder(
                        "Deleted users account successfully!.",
                        HttpStatus.OK,
                        taskInfo
                );
            } else {
                return ResponseHandler.errorResponseBuilder(
                        "Deleted users cannot be successfully!.",
                        HttpStatus.BAD_REQUEST
                );
            }
        } catch (DataAccessException e) {
            throw new NotFoundException("Create task cannot be successfully!" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getListting() {
       try {
           List<TaskEntity> userOptional = taskReposirtory.findAll();

           return ResponseHandler.successResponseBuilder(
                   "Get listting task successfully!.",
                   HttpStatus.OK,
                   userOptional
                   );
       } catch (DataAccessException e) {
       }
        return null;
    }

    @Override
    public Object getTask(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTask'");
    }

}
