package com.home.login.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionIntercept {

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity entityNotFoundException() {
            return ResponseEntity.notFound().build();
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
            var erros = e.getFieldErrors();
            return ResponseEntity.badRequest().body(erros.stream().map(DataValidationError::new).toList());
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity httpMessageNotReadableException(HttpMessageNotReadableException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity badCredentialsException() {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity authenticationException() {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity accessDeniedException() {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
        }

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<String> handleDefaultException(DefaultException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }

        private record DataValidationError(String field, String value) {
            public DataValidationError(FieldError error) {
                this(error.getField(), error.getDefaultMessage());
            }
        }
    }
