package br.com.gabrielferreira.usuario.dto;

import br.com.gabrielferreira.usuario.dto.response.TipoTelefoneResponseDTO;

import java.time.ZonedDateTime;

public record TelefoneDTO(Long id, String numero, String ddd, String descricao, TipoTelefoneResponseDTO tipoTelefone, ZonedDateTime createdAt,
                          ZonedDateTime updatedAt) {
}
