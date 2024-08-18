package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record TipoTelefoneDTO(
        @Schema(description = "ID do tipo de telefone", example = "4")
        Long id,

        @Schema(description = "Descrição do tipo de telefone", example = "Residencial")
        String descricao,

        @Schema(description = "Código do tipo de telefone", example = "RESIDENCIAL")
        String codigo,

        @Schema(description = "Tipo do telefone")
        TipoTipoTelefoneDTO tipo
) implements Serializable {
}
