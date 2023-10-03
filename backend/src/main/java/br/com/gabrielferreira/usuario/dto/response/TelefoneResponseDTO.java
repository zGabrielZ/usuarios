package br.com.gabrielferreira.usuario.dto.response;

import java.time.ZonedDateTime;

public record TelefoneResponseDTO(Long id, String numero, String ddd, String telefoneFormatado, String descricao, TipoTelefoneResponseDTO tipoTelefone, ZonedDateTime createdAt,
                                  ZonedDateTime updatedAt) {
}
