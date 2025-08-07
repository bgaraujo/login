package com.home.login.exception;

import com.home.login.exception.enums.ExceptionMessages;

public class UniqueException extends DefaultException {
    public UniqueException() {
        super(ExceptionMessages.UNIQUE_FIELD);
    }
}