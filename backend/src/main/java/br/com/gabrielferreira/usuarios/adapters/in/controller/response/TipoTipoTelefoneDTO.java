package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record TipoTipoTelefoneDTO(
        @Schema(description = "ID do tipo de telefone", example = "2")
        Long id,

        @Schema(description = "Descrição do tipo de telefone", example = "Tipo de telefone")
        String descricao,

        @Schema(description = "Código do tipo de telefone", example = "TIPO_TELEFONE")
        String codigo
) implements Serializable {
}
