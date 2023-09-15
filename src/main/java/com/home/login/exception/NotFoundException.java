package com.home.login.exception;

import lombok.Getter;

import static com.home.login.exception.enums.ExceptionMessages.USER_NOT_FOUND;

@Getter
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super(USER_NOT_FOUND.getMessage());
    }
}