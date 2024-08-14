package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import java.io.Serializable;

public record DominioDTO(
        Long id,
        String descricao,
        String codigo,
        TipoDominioDTO tipo
) implements Serializable {
}
