package com.snowball.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommomError extends RuntimeException{
    private final ErrorCode error;
}
