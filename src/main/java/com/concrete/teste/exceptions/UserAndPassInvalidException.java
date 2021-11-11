package com.concrete.teste.exceptions;

import org.springframework.http.HttpStatus;

public class UserAndPassInvalidException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String MSG = "Usuário e/ou senha inválidos";

    public UserAndPassInvalidException(HttpStatus httpStatus) {

        super(MSG);
    }
}
