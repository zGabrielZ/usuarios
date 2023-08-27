package br.com.gabrielferreira.usuario.exception;

import java.io.Serial;

public class NaoEncontradoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3422755866458974770L;

    public NaoEncontradoException(String msg){
        super(msg);
    }
}
