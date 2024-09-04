package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record SituacaoAnotacaoEstudoDTO(
        @Schema(description = "ID da situação da anotação", example = "9")
        Long id,

        @Schema(description = "Descrição da situação da anotação", example = "Estudo em andamento")
        String descricao,

        @Schema(description = "Código da situação da anotação", example = "ESTUDO_ANDAMENTO")
        String codigo,

        @Schema(description = "Tipo da situação da anotação")
        SituacaoTipoAnotacaoDTO tipo
) implements Serializable {
}
