package br.com.gabrielferreira.usuarios.application.exception.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ErroPadraoFormulario(
        String campo,
        String mensagem
) implements Serializable {
}
