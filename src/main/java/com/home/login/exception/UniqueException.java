package com.home.login.exception;

import com.home.login.exception.enums.ExceptionMessages;

import static com.home.login.exception.enums.ExceptionMessages.UNIQUE_FIELD;

public class UniqueException extends RuntimeException {

    public UniqueException() {
        super(UNIQUE_FIELD.getMessage());
    }

}
