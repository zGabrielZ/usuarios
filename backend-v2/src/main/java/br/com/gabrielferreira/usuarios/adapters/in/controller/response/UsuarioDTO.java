package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public record UsuarioDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String cpfFormatado,
        BigDecimal renda,
        String rendaFormatada,
        LocalDate dataNascimento,
        Integer quantidadeFilhos,
        TelefoneDTO telefone,
        DominioDTO genero,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) implements Serializable {
}
