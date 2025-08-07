package com.home.login.exception.enums;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionMessages{
    USER_NOT_FOUND("Usuario não encontrado", HttpStatus.BAD_REQUEST),
    UNIQUE_FIELD("Usuario ja cadastrado", HttpStatus.BAD_REQUEST),
    PK_KEY_ERROR("Erro ao obter chave publica/privada", HttpStatus.BAD_REQUEST),
    INVALID_USARNAME_OR_PASSWORD("Nome de usuario ou senha invalido", HttpStatus.BAD_REQUEST),
    ;

    private String message;
    private HttpStatus httpStatus;

    ExceptionMessages(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
