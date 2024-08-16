package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public record UsuarioResumidoDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String cpfFormatado,
        BigDecimal renda,
        String rendaFormatada,
        LocalDate dataNascimento,
        Integer quantidadeFilhos,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) implements Serializable {
}
