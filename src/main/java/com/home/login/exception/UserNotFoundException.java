package com.home.login.exception;

import com.home.login.exception.enums.ExceptionMessages;

public class UserNotFoundException extends DefaultException {
    public UserNotFoundException() {
        super(ExceptionMessages.USER_NOT_FOUND);
    }
}