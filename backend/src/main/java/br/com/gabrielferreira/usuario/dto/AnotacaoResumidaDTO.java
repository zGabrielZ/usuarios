package br.com.gabrielferreira.usuario.dto;

import java.time.ZonedDateTime;

public record AnotacaoResumidaDTO(Long id, String titulo, String descricao, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
}
