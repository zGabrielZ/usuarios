package br.com.gabrielferreira.usuarios.application.exception;

import java.io.Serial;

public class ForbiddenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4918713994454109857L;

    public ForbiddenException(String msg) {
        super(msg);
    }
}
