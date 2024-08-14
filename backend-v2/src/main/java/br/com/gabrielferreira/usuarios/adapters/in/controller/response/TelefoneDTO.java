package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record TelefoneDTO(
        Long id,
        String numero,
        String ddd,
        String telefoneFormatado,
        String descricao,
        DominioDTO tipoTelefone,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) implements Serializable {
}
