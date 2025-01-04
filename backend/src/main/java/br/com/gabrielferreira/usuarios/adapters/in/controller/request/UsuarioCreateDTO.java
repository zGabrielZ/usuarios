package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record UsuarioCreateDTO(
        @Schema(description = "Nome do usuário", example = "Gabriel Ferreira")
        @NotBlank
        @Size(max = 155)
        String nome,

        @Schema(description = "E-mail do usuário", example = "test@email.com")
        @NotBlank
        @Email
        String email,

        @Schema(description = "Senha do usuário", example = "123")
        @NotBlank
        @Size(min = 1, max = 255)
        String senha,

        @Schema(description = "CPF do usuário", example = "46188190061")
        @Pattern(regexp = "\\d+")
        @CPF
        @NotBlank
        @Size(max = 11)
        String cpf,

        @Schema(description = "Renda do usuário", example = "2500.00")
        @Digits(integer = 12, fraction = 2)
        @PositiveOrZero
        BigDecimal renda,

        @Schema(description = "Data de nascimento do usuário (ANO/MÊS/DIA)", example = "1997-12-26")
        @NotNull
        @PastOrPresent
        LocalDate dataNascimento,

        @Schema(description = "Quantidade de filhos do usuário", example = "2")
        @PositiveOrZero
        Integer quantidadeFilhos,

        @Schema(description = "Telefone do usuário")
        @Valid
        @NotNull
        TelefoneCreateDTO telefone,

        @Schema(description = "Gênero do usuário")
        @Valid
        @NotNull
        GeneroCreateDTO genero
) implements Serializable {
}
