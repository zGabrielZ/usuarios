package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record AnotacaoLembreteCreateDTO(
        @Schema(description = "Título da anotação", example = "Anotação #123")
        @NotBlank
        @Size(max = 155)
        String titulo,

        @Schema(description = "Descrição da anotação", example = "Anotação tal.....")
        @NotBlank
        String descricao,

        @Future
        @NotBlank
        @Schema(description = "Data lembrete da anotação", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime dataLembrete
) implements Serializable {
}
