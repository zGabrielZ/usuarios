package br.com.gabrielferreira.usuario.dto.response;

import java.time.ZonedDateTime;

public record AnotacaoResumidaResponseDTO(Long id, String titulo, String descricao, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
}
