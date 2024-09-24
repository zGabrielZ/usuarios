package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public record UsuarioDTO(
        @Schema(description = "ID do usuário", example = "1")
        Long id,

        @Schema(description = "Nome do usuário", example = "Gabriel Ferreira")
        String nome,

        @Schema(description = "E-mail do usuário", example = "test@email.com")
        String email,

        @Schema(description = "CPF do usuário", example = "46188190061")
        String cpf,

        @Schema(description = "CPF formatado do usuário", example = "461.881.900-61")
        String cpfFormatado,

        @Schema(description = "Renda do usuário", example = "2500.00")
        BigDecimal renda,

        @Schema(description = "Renda formatada do usuário", example = "R$ 2.500,00")
        String rendaFormatada,

        @Schema(description = "Data de nascimento do usuário (ANO/MÊS/DIA)", example = "1997-12-26")
        LocalDate dataNascimento,

        @Schema(description = "Quantidade de filhos do usuário", example = "2")
        Integer quantidadeFilhos,

        @Schema(description = "Telefone do usuário")
        TelefoneDTO telefone,

        @Schema(description = "Gênero do usuário")
        GeneroDTO genero,

        @Schema(description = "Criação do usuário", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime createdAt,

        @Schema(description = "Edição do usuário", example = "2024-08-18T15:21:37.7822381-03:00")
        ZonedDateTime updatedAt
) implements Serializable {
}
