package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record GeneroDTO(
        @Schema(description = "ID do gênero", example = "1")
        Long id,

        @Schema(description = "Descrição do gênero", example = "Masculino")
        String descricao,

        @Schema(description = "Código do gênero", example = "MASCULINO")
        String codigo,

        @Schema(description = "Tipo do gênero")
        TipoGeneroDTO tipo
) implements Serializable {
}
