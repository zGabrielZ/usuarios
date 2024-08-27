package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record TipoTipoAnotacaoDTO(
        @Schema(description = "ID do tipo de anotação", example = "3")
        Long id,

        @Schema(description = "Descrição do tipo de anotação", example = "Tipo de anotação")
        String descricao,

        @Schema(description = "Código do tipo de anotação", example = "TIPO_ANOTACAO")
        String codigo
) implements Serializable {
}
