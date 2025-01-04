package br.com.gabrielferreira.usuarios.application.exception;

import java.io.Serial;

public class UnauthorizedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2486784081764177369L;

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
