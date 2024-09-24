package br.com.gabrielferreira.usuarios.application.exception;

import java.io.Serial;

public class MsgException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7464642184758130939L;

    public MsgException(String msg){
        super(msg);
    }
}
