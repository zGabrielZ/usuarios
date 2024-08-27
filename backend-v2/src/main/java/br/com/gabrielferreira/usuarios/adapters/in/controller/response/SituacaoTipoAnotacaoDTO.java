package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record SituacaoTipoAnotacaoDTO(
        @Schema(description = "ID da situação do tipo de anotação", example = "4")
        Long id,

        @Schema(description = "Descrição da situação do tipo de anotação", example = "Situação do tipo de anotação")
        String descricao,

        @Schema(description = "Código da situação do tipo de anotação", example = "SITUACAO_TIPO_ANOTACAO")
        String codigo
) implements Serializable {
}
