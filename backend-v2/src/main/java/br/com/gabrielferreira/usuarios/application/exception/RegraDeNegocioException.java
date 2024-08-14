package br.com.gabrielferreira.usuarios.application.exception;

import java.io.Serial;

public class RegraDeNegocioException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7464642184758130939L;

    public RegraDeNegocioException(String msg){
        super(msg);
    }
}
