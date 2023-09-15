package com.home.login.exception.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ExceptionMessages{
    USER_NOT_FOUND("Usuario não encontrado"),
    UNIQUE_FIELD("Usuario ja cadastrado");

    private String message;

    ExceptionMessages(String message) {
        this.message = message;
    }
}
