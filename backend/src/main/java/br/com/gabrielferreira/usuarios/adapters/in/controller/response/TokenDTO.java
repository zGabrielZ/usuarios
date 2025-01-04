package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record TokenDTO(
        @Schema(description = "Tipo do token", example = "Bearer")
        String tipo,

        @Schema(description = "Token", example = "WQQWOFQJWFPQJEFQFQ")
        String token
) implements Serializable {
}
