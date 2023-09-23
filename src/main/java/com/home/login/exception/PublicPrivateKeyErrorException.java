package com.home.login.exception;

import lombok.Getter;

import static com.home.login.exception.enums.ExceptionMessages.PK_KEY_ERROR;

@Getter
public class PublicPrivateKeyErrorException extends RuntimeException{
    public PublicPrivateKeyErrorException() {
        super(PK_KEY_ERROR.getMessage());
    }
}