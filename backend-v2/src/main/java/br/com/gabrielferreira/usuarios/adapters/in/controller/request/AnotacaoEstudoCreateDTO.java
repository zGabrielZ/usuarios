package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record AnotacaoEstudoCreateDTO(
        @Schema(description = "Título da anotação", example = "Anotação #123")
        @NotBlank
        @Size(max = 155)
        String titulo,

        @Schema(description = "Descrição da anotação", example = "Anotação tal.....")
        @NotBlank
        String descricao,

        @FutureOrPresent
        @NotNull
        @Schema(description = "Data estudo inicío", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime dataEstudoInicio,

        @FutureOrPresent
        @NotNull
        @Schema(description = "Data estudo fim", example = "2024-08-18T16:21:37.7822381-03:00")
        ZonedDateTime dataEstudoFim
) implements Serializable {
}
