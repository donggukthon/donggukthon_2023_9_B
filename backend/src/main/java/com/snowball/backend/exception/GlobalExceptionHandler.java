package com.snowball.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity handleCustomException(CustomException ex) {
        return new ResponseEntity(
                new ExceptionDto(
                        ex.getError().getErrorCode(),
                        ex.getError().getMessage()
                ),
                ex.getError().getHttpStatus()
        );
    }

    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity handleServerException(Exception ex) {
        return new ResponseEntity(new ExceptionDto(ErrorCode.INTERNAL_SERVER_ERROR.getErrorCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}