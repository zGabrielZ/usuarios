package br.com.gabrielferreira.usuario.dto;

import java.time.Instant;

public record GeneroDTO(Long id, String descricao, String codigo, Instant createdAt, Instant updatedAt) {
}
