package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AnotacaoRascunhoCreateDTO(
        @Schema(description = "Título da anotação", example = "Anotação #123")
        @NotBlank
        @Size(max = 155)
        String titulo,

        @Schema(description = "Descrição da anotação", example = "Anotação tal.....")
        @NotBlank
        String descricao
) implements Serializable {
}
