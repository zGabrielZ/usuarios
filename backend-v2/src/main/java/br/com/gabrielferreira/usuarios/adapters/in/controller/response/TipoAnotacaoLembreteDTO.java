package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record TipoAnotacaoLembreteDTO(
        @Schema(description = "ID do tipo de anotação", example = "8")
        Long id,

        @Schema(description = "Descrição do tipo de anotação", example = "Lembrete")
        String descricao,

        @Schema(description = "Código do tipo de anotação", example = "LEMBRETE")
        String codigo,

        @Schema(description = "Tipo de anotação")
        TipoTipoAnotacaoDTO tipo
) implements Serializable {
}
