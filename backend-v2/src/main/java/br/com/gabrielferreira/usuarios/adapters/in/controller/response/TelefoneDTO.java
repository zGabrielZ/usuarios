package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record TelefoneDTO(
        @Schema(description = "ID de telefone do usuário", example = "1")
        Long id,

        @Schema(description = "Número de telefone do usuário", example = "34655691")
        String numero,

        @Schema(description = "DDD do número do telefone do usuário", example = "11")
        String ddd,

        @Schema(description = "Número de telefone formatado", example = "(11) 3465-5691")
        String telefoneFormatado,

        @Schema(description = "Descrição de telefone do usuário", example = "Número da tal pessoa...")
        String descricao,

        @Schema(description = "Tipo de telefone do telefone do usuário")
        TipoTelefoneDTO tipoTelefone,

        @Schema(description = "Criação do telefone", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime createdAt,

        @Schema(description = "Edição do telefone", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime updatedAt
) implements Serializable {
}
