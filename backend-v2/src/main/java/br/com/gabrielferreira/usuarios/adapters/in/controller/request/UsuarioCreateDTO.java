package br.com.gabrielferreira.usuarios.adapters.in.controller.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record UsuarioCreateDTO(
        String nome,
        String email,
        String cpf,
        BigDecimal renda,
        LocalDate dataNascimento,
        Integer quantidadeFilhos,
        TelefoneCreateDTO telefone,
        GeneroCreateDTO genero
) implements Serializable {
}
