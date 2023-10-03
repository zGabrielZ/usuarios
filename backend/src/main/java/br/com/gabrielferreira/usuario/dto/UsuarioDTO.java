package br.com.gabrielferreira.usuario.dto;

import br.com.gabrielferreira.usuario.dto.response.GeneroResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public record UsuarioDTO(Long id, String nome, String email, String cpf, BigDecimal renda, LocalDate dataNascimento,
                         Integer quantidadeFilhos, TelefoneDTO telefone, GeneroResponseDTO genero, ZonedDateTime createdAt,
                         ZonedDateTime updatedAt) {
}
