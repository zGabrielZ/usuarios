package br.com.gabrielferreira.usuario.dto;

import br.com.gabrielferreira.usuario.dto.response.UsuarioResponseDTO;

import java.time.ZonedDateTime;

public record AnotacaoDTO(Long id, String titulo, String descricao, UsuarioResponseDTO usuario, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
}
