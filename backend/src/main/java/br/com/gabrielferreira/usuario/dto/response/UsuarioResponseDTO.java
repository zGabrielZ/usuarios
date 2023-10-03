package br.com.gabrielferreira.usuario.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public record UsuarioResponseDTO(Long id, String nome, String email, String cpf, String cpfFormatado, BigDecimal renda, String rendaFormatada, LocalDate dataNascimento,
                                 Integer quantidadeFilhos, TelefoneResponseDTO telefone, GeneroResponseDTO genero, ZonedDateTime createdAt,
                                 ZonedDateTime updatedAt) {
}
