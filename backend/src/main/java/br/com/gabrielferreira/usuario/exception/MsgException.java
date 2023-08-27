package br.com.gabrielferreira.usuario.exception;

import java.io.Serial;

public class MsgException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2259006397246859174L;

    public MsgException(String msg){
        super(msg);
    }
}
