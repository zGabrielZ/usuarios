package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record TelefoneCreateDTO(
        @Schema(description = "Número de telefone do usuário", example = "34655691")
        @Pattern(regexp = "^[0-9]+$")
        @NotBlank
        @Size(min = 8, max = 9)
        String numero,

        @Schema(description = "DDD do número do telefone do usuário", example = "11")
        @Pattern(regexp = "^[0-9]+$")
        @NotBlank
        @Size(min = 2, max = 2)
        String ddd,

        @Schema(description = "Descrição de telefone do usuário", example = "Número da tal pessoa...")
        @Size(max = 500)
        String descricao,

        @Schema(description = "Tipo de telefone do telefone do usuário")
        @Valid
        @NotNull
        TipoTelefoneCreateDTO tipoTelefone
) implements Serializable {
}
