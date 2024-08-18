package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record TelefoneCreateDTO(
        @Pattern(regexp = "^[0-9]+$")
        @NotBlank
        @Size(min = 8, max = 9)
        String numero,

        @Pattern(regexp = "^[0-9]+$")
        @NotBlank
        @Size(min = 2, max = 2)
        String ddd,

        @Size(max = 500)
        String descricao,

        @Valid
        @NotNull
        TipoTelefoneCreateDTO tipoTelefone
) implements Serializable {
}
