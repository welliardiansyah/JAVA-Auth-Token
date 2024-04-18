package com.auth_services.auth_services.exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException
            (NotFoundException notFoundException)
    {
        Exception exception = new Exception(
                notFoundException.getMessage(),
                notFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
