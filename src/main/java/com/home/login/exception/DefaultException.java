package com.home.login.exception;

import com.home.login.exception.enums.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class DefaultException extends RuntimeException {
    private final HttpStatus httpStatus;

    public DefaultException(ExceptionMessages exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.httpStatus = exceptionMessage.getHttpStatus();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}