package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import java.io.Serializable;

public record TipoDominioDTO(
        Long id,
        String descricao,
        String codigo
) implements Serializable {
}
