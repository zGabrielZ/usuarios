package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record SituacaoAnotacaoDTO(
        @Schema(description = "ID da situação da anotação", example = "11")
        Long id,

        @Schema(description = "Descrição da situação da anotação", example = "Rascunho em aberto")
        String descricao,

        @Schema(description = "Código da situação da anotação", example = "RASCUNHO_ABERTO")
        String codigo,

        @Schema(description = "Tipo da situação da anotação")
        SituacaoTipoAnotacaoDTO tipo
) implements Serializable {
}
