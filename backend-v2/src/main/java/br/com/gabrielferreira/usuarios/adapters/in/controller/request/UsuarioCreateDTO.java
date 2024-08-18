package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record UsuarioCreateDTO(
        @NotBlank
        @Size(max = 155)
        String nome,

        @NotBlank
        @Email
        String email,

        @Pattern(regexp = "^[0-9]+$")
        @CPF
        @NotBlank
        @Size(max = 11)
        String cpf,

        @Digits(integer = 12, fraction = 2)
        @PositiveOrZero
        BigDecimal renda,

        @NotNull
        @PastOrPresent
        LocalDate dataNascimento,

        @PositiveOrZero
        Integer quantidadeFilhos,

        @Valid
        @NotNull
        TelefoneCreateDTO telefone,

        @Valid
        @NotNull
        GeneroCreateDTO genero
) implements Serializable {
}
