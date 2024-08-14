package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import java.io.Serializable;

public record TelefoneCreateDTO(
        String numero,
        String ddd,
        String descricao,
        TipoTelefoneCreateDTO tipoTelefone
) implements Serializable {
}
