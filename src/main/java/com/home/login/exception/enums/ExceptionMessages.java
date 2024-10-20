package com.home.login.exception.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ExceptionMessages{
    USER_NOT_FOUND("Usuario não encontrado"),
    UNIQUE_FIELD("Usuario ja cadastrado"),
    PK_KEY_ERROR("Erro ao obter chave publica/privada"),
    INVALID_USARNAME_OR_PASSWORD("Nome de usuario ou senha invalido"),
    ;

    private String message;

    ExceptionMessages(String message) {
        this.message = message;
    }
}
