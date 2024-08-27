package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record TipoAnotacaoDTO(
        @Schema(description = "ID do tipo de anotação", example = "1")
        Long id,

        @Schema(description = "Descrição do tipo de anotação", example = "Rascunho")
        String descricao,

        @Schema(description = "Código do tipo de anotação", example = "ESTUDO")
        String codigo,

        @Schema(description = "Tipo de anotação")
        TipoTipoAnotacaoDTO tipo
) implements Serializable {
}
