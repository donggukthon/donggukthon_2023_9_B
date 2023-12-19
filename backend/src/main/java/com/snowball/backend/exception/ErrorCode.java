package com.snowball.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // Not Found Error
    NOT_FOUND("40400", HttpStatus.NOT_FOUND, "해당 리소스를 찾을 수 없습니다."),
    NOT_END_POINT("40402", HttpStatus.NOT_FOUND, "존재하지 않는 엔드포인트입니다."),
    NOT_FOUND_USER("40403", HttpStatus.NOT_FOUND, "해당 사용자가 존재하지 않습니다."),

    // Invalid Argument Error
    BAD_REQUEST("40000", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    METHOD_NOT_ALLOWED("40001", HttpStatus.BAD_REQUEST, "지원하지 않는 HTTP Method 입니다."),

    // Unauthorized Error

    // Forbidden Error
    ACCESS_DENIED("40300", HttpStatus.FORBIDDEN, "접근이 거부되었습니다."),

    // Internal Server Error
    INTERNAL_SERVER_ERROR("50000", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러입니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
