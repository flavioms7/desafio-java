package com.concrete.teste.exceptions;

import org.springframework.http.HttpStatus;

public class SessaoInvalidaException extends RuntimeException {

    /**
     * - Exception para informar que a sessão expirou
     */
    private static final long serialVersionUID = 1L;
    public static final String MSG = "Sessão inválida";

    public SessaoInvalidaException(HttpStatus httpStatus) {

        super(MSG);

    }
}

