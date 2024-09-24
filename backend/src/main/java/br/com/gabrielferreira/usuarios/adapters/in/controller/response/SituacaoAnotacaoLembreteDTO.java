package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record SituacaoAnotacaoLembreteDTO(
        @Schema(description = "ID da situação da anotação", example = "13")
        Long id,

        @Schema(description = "Descrição da situação da anotação", example = "Lembrete em aberto")
        String descricao,

        @Schema(description = "Código da situação da anotação", example = "LEMBRETE_ABERTO")
        String codigo,

        @Schema(description = "Tipo da situação da anotação")
        SituacaoTipoAnotacaoDTO tipo
) implements Serializable {
}
