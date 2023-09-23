package com.home.login.exception;

import lombok.Getter;

import static com.home.login.exception.enums.ExceptionMessages.INVALID_USARNAME_OR_PASSWORD;

@Getter
public class LoginException extends RuntimeException{
    public LoginException() {
        super(INVALID_USARNAME_OR_PASSWORD.getMessage());
    }
}