package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.exception.BadRequestException;
import com.codecool.ecosampler.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsAdvisory extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        String errorMessage = "Bad Request: " + ex.getMessage();
        ApiError apiError = new ApiError(errorMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        String errorMessage = "Not Found: " + ex.getMessage();
        ApiError apiError = new ApiError(errorMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleExceptionInternal(UsernameNotFoundException ex, WebRequest request) {
        String errorMessage = "Not Found: " + ex.getMessage();
        ApiError apiError = new ApiError(errorMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationExceptionInternal(AuthenticationException ex, WebRequest request) {
        String errorMessage = "Invalid credentials";
        ApiError apiError = new ApiError(errorMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

        public record ApiError(String message) {
    }
}

