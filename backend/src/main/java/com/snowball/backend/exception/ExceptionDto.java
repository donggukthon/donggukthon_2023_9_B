package com.snowball.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public final class ExceptionDto {
    private final String errorCode;
    private final String message;
}
