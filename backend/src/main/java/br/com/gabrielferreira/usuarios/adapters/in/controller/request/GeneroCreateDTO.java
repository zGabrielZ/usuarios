package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record GeneroCreateDTO(
        @Schema(description = "ID do gênero do usuário", example = "1")
        @NotNull
        Long id
) implements Serializable {
}
