package br.com.gabrielferreira.usuarios.application.exception.model;

import lombok.Builder;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record ErroPadrao(
        ZonedDateTime dataAtual,
        Integer status,
        String titulo,
        String mensagem,
        String caminhoUrl,
        List<ErroPadraoFormulario> campos
) implements Serializable {
}
