package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record TipoTelefoneCreateDTO(
        @Schema(description = "ID do tipo de telefone do usuário", example = "4")
        @NotNull
        Long id
) implements Serializable {
}
