package br.com.gabrielferreira.usuario.dto;

import java.time.ZonedDateTime;

public record AnotacaoDTO(Long id, String descricao, UsuarioDTO usuario, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
}
