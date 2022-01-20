package com.example.recipe_jpa.controller;

import com.example.recipe_jpa.exception.NotFoundException;
import com.example.recipe_jpa.model.dto.view.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandlerController {

    public ExceptionResponseDto build(String message, HttpStatus status, WebRequest request){
        return new ExceptionResponseDto(
                LocalDateTime.now(),
                status.value(),
                status.name(),
                message,
                request.getDescription(false)
        );
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> handleIllegalArgumentException(IllegalArgumentException ex ,WebRequest request){
        return ResponseEntity.badRequest().body(build(ex.getMessage(),HttpStatus.BAD_REQUEST,request));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerNotFoundException(NotFoundException ex, WebRequest request){
        return ResponseEntity.status(404).body(
                build(ex.getMessage(),HttpStatus.NOT_FOUND,request)
        );
    }
}
