package com.concrete.teste.exceptions;

import org.springframework.http.HttpStatus;

public class EmailJaCadastradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String MSG = "E-mail already exists";

    public EmailJaCadastradoException(HttpStatus httpStatus) {

        super(MSG);
    }
}
