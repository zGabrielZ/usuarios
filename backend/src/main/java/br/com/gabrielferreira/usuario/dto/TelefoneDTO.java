package br.com.gabrielferreira.usuario.dto;

import java.time.ZonedDateTime;

public record TelefoneDTO(Long id, String numero, String ddd, String descricao, TipoTelefoneDTO tipoTelefone, ZonedDateTime createdAt,
                          ZonedDateTime updatedAt) {
}
