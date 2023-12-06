package com.practice.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {


    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appExceptionHandler (AppException e){ // 요청이 왔을때  앱 익셉션이 발생하면 해당 함수로 들어옴
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode().name() +" "+e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler (RuntimeException e){ // 요청이 왔을때 런타임 익셉션이 발생하면 해당 함수로 들어옴
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}
