package com.auth_services.auth_services.service.Impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auth_services.auth_services.entity.TaskEntity;
import com.auth_services.auth_services.entity.TaskUserEntity;
import com.auth_services.auth_services.entity.UserEntity;
import com.auth_services.auth_services.exception.NotFoundException;
import com.auth_services.auth_services.repository.TaskReposirtory;
import com.auth_services.auth_services.repository.TaskUserRepository;
import com.auth_services.auth_services.repository.UserRepository;
import com.auth_services.auth_services.response.ResponseHandler;
import com.auth_services.auth_services.service.TaskUserService;

@Service
public class TaskUserImplService implements TaskUserService{
    TaskUserRepository taskUserRepository;
    TaskReposirtory taskReposirtory;
    UserRepository userRepository;

    @Autowired
    public TaskUserImplService(TaskUserRepository taskUserRepository, TaskReposirtory taskReposirtory, UserRepository userRepository) {
        this.taskUserRepository = taskUserRepository;
        this.taskReposirtory = taskReposirtory;
        this.userRepository = userRepository;
    }
    
    @Override
    public ResponseEntity<Object> createTaskUser(TaskUserEntity taskUserEntity) {
        String usersId = taskUserEntity.getUsers();
        UUID convertID = UUID.fromString(usersId);
        Optional<UserEntity> getUsers = userRepository.findById(convertID);
        if (!getUsers.isPresent()){
            return ResponseHandler.errorResponseBuilder(
                    "User anda tidak dapat ditemuakn!.",
                    HttpStatus.NOT_FOUND
            );
        }

        TaskEntity taskId = taskUserEntity.getTask();
        Optional<TaskEntity> getTask = taskReposirtory.findById(taskId.getId());
        if (!getTask.isPresent()){
            return ResponseHandler.errorResponseBuilder(
                    "Task anda tidak dapat ditemuakn!.",
                    HttpStatus.NOT_FOUND
            );
        }

        try {
            TaskUserEntity createData = taskUserRepository.save(taskUserEntity);
            if (createData != null) {
                Map<String, Object> users = new LinkedHashMap<>();
                users.put("id", createData.getId());
                users.put("users", createData.getUsers());
                users.put("task_id", createData.getTask().getId());
                users.put("finished", createData.getIs_finished());
                users.put("created_at", createData.getCreatedAt());
                users.put("updated_at", createData.getUpdatedAt());

                return ResponseHandler.successResponseBuilder(
                        "Create task users successfully!.",
                        HttpStatus.OK,
                        users
                );
            } else {
                return ResponseHandler.errorResponseBuilder(
                        "Create task users cannot be successfully!.",
                        HttpStatus.OK
                );
            }
        } catch (DataAccessException e) {
            throw new NotFoundException("Created task user cannot successfully!." + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> updateTaskUser(TaskUserEntity taskUserEntity) {
        if (!taskUserRepository.existsById(taskUserEntity.getId())) {
            return ResponseHandler.errorResponseBuilder(
                    "Task user anda tidak dapat ditemuakn!.",
                    HttpStatus.NOT_FOUND
            );
        }

        String usersId = taskUserEntity.getUsers();
        UUID convertID = UUID.fromString(usersId);
        Optional<UserEntity> getUsers = userRepository.findById(convertID);
        if (!getUsers.isPresent()){
            return ResponseHandler.errorResponseBuilder(
                    "User anda tidak dapat ditemuakn!.",
                    HttpStatus.NOT_FOUND
            );
        }

        TaskEntity taskId = taskUserEntity.getTask();
        Optional<TaskEntity> getTask = taskReposirtory.findById(taskId.getId());
        if (!getTask.isPresent()){
            return ResponseHandler.errorResponseBuilder(
                    "Task anda tidak dapat ditemuakn!.",
                    HttpStatus.NOT_FOUND
            );
        }

        try {
            TaskUserEntity createData = taskUserRepository.save(taskUserEntity);
            if (createData != null) {
                Map<String, Object> users = new LinkedHashMap<>();
                users.put("id", createData.getId());
                users.put("users", createData.getUsers());
                users.put("task_id", createData.getTask().getId());
                users.put("finished", createData.getIs_finished());
                users.put("created_at", createData.getCreatedAt());
                users.put("updated_at", createData.getUpdatedAt());

                return ResponseHandler.successResponseBuilder(
                        "Create task users successfully!.",
                        HttpStatus.OK,
                        users
                );
            } else {
                return ResponseHandler.errorResponseBuilder(
                        "Create task users cannot be successfully!.",
                        HttpStatus.OK
                );
            }
        } catch (DataAccessException e) {
            throw new NotFoundException("Created task user cannot successfully!." + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> deleteTaskUser(UUID id) {
        Optional<TaskUserEntity> existingUser = taskUserRepository.findById(id);
        TaskUserEntity user = existingUser.get();

        Map<String, Object> taskInfo = new LinkedHashMap<>();
        taskInfo.put("id", user.getId());
        taskInfo.put("users", user.getUsers());
        taskInfo.put("task_id", user.getTask().getId());
        taskInfo.put("finished", user.getIs_finished());
        taskInfo.put("created_at", user.getCreatedAt());
        taskInfo.put("updated_at", user.getUpdatedAt());

        try {
            if (existingUser.isPresent()) {
                taskUserRepository.delete(user); 
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
    public Object getTaskUser(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTaskUser'");
    }

    @Override
    public ResponseEntity<Object> getListting() {
       try {
           List<TaskUserEntity> userOptional = taskUserRepository.findAll();

           return ResponseHandler.successResponseBuilder(
                   "Get listting task users successfully!.",
                   HttpStatus.OK,
                   userOptional
                   );
       } catch (DataAccessException e) {
       }
        return null;
    }

}
