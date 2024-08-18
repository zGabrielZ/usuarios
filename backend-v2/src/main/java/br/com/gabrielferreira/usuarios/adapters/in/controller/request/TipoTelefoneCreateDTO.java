package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record TipoTelefoneCreateDTO(
        @NotNull
        Long id
) implements Serializable {
}
