package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record TipoGeneroDTO(
        @Schema(description = "ID do tipo de gênero", example = "1")
        Long id,

        @Schema(description = "Descrição do gênero", example = "Gênero")
        String descricao,

        @Schema(description = "Código do gênero", example = "GENERO")
        String codigo
) implements Serializable {
}
