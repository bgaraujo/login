package com.home.login.exception;

import static com.home.login.exception.enums.ExceptionMessages.INVALID_USARNAME_OR_PASSWORD;

public class LoginException extends DefaultException {
    public LoginException() {
        super(INVALID_USARNAME_OR_PASSWORD);
    }
}