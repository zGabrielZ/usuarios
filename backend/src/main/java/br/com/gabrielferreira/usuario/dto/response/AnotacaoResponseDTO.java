package br.com.gabrielferreira.usuario.dto.response;

import java.time.ZonedDateTime;

public record AnotacaoResponseDTO(Long id, String titulo, String descricao, UsuarioResponseDTO usuario, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
}
