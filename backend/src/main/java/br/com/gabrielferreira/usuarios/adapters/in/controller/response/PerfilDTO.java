package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record PerfilDTO(
        @Schema(description = "ID do perfil", example = "1")
        Long id,

        @Schema(description = "Título do perfil", example = "Adminstrador")
        String titulo,

        @Schema(description = "Autoriedade do perfil", example = "ROLE_ADMIN")
        String autoriedade
) implements Serializable {
}
