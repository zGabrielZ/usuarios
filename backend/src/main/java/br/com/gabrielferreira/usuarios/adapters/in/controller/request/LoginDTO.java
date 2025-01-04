package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record LoginDTO(
        @Schema(description = "E-mail do usuário", example = "teste@email.com")
        @Email
        @NotBlank
        @Size(min = 1, max = 255)
        String email,

        @Schema(description = "Senha do usuário", example = "123")
        @NotBlank
        @Size(min = 1, max = 255)
        String senha
) implements Serializable {
}
