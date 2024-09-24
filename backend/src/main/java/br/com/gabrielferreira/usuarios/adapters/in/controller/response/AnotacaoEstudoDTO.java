package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record AnotacaoEstudoDTO(
        @Schema(description = "ID da anotação", example = "1")
        Long id,

        @Schema(description = "Título da anotação", example = "Anotação #123")
        String titulo,

        @Schema(description = "Descrição da anotação", example = "Tal anotação.....")
        String descricao,

        @Schema(description = "Tipo anotação")
        TipoAnotacaoEstudoDTO tipoAnotacao,

        @Schema(description = "Situação da anotação")
        SituacaoAnotacaoEstudoDTO situacaoTipoAnotacao,

        @Schema(description = "Data estudo inicío", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime dataEstudoInicio,

        @Schema(description = "Data estudo fim", example = "2024-08-18T16:21:37.7822381-03:00")
        ZonedDateTime dataEstudoFim,

        @Schema(description = "Criação da anotação", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime createdAt,

        @Schema(description = "Edição da anotação", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime updatedAt
) implements Serializable {
}
