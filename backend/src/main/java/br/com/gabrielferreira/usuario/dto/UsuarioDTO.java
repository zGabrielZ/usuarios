package br.com.gabrielferreira.usuario.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public record UsuarioDTO(Long id, String nome, String email, String cpf, BigDecimal renda, LocalDate dataNascimento,
                         Integer quantidadeFilhos, TelefoneDTO telefone, GeneroDTO genero, ZonedDateTime createdAt,
                         ZonedDateTime updatedAt) {
}
