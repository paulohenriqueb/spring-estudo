package com.minhaempresa.spring.application.resources.Exceptions;

import com.minhaempresa.spring.application.services.exceptions.DatabaseException;
import com.minhaempresa.spring.application.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException,
            HttpServletRequest httpServletRequest){
        String errorMessage = "Resource not found";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(
                Instant.now(),
                httpStatus.value(), errorMessage, resourceNotFoundException.getMessage(),
                httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> dataBaseException(
            DatabaseException databaseException,
            HttpServletRequest httpServletRequest){
        String errorMessage = "Database Error";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(
                Instant.now(),
                httpStatus.value(), errorMessage,databaseException.getMessage(),
                httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}
