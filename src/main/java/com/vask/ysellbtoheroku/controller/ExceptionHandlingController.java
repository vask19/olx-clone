package com.vask.ysellbtoheroku.controller;
import com.vask.ysellbtoheroku.dto.ApiErrorDto;
import com.vask.ysellbtoheroku.exception.ChatNotFoundException;
import com.vask.ysellbtoheroku.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException productNotFoundException, WebRequest webRequest){
        return new ResponseEntity<>(
                new ApiErrorDto(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                     HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler({UsernameNotFoundException.class,UsernameNotFoundException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException authenticationException, WebRequest webRequest){
        return new ResponseEntity<>(
                new ApiErrorDto(authenticationException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);


    }
    @ExceptionHandler(ChatNotFoundException.class)
    public ResponseEntity<Object> handleChatNotFound(ChatNotFoundException chatNotFoundException, WebRequest webRequest){
        return new ResponseEntity<>(
                new ApiErrorDto(chatNotFoundException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);


    }
    @ExceptionHandler(ServletException.class)
    public ResponseEntity<Object> handleServletException(ServletException servletException, WebRequest webRequest){
        return new ResponseEntity<>(
                new ApiErrorDto(servletException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler({ RuntimeException.class})
    public ResponseEntity<Object> handleAccessDeniedException(RuntimeException internalServerError, WebRequest webRequest){
        return new ResponseEntity<>(
                new ApiErrorDto(internalServerError.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);


    }

}

