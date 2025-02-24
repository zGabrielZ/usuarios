package br.com.gabrielferreira.usuarios.infrastructure.config.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ErroPadraoFormulario(
        String campo,
        String mensagem
) implements Serializable {
}
