package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface FindUsuarioInput {

    UsuarioDomain findByCpf(String cpf);

    UsuarioDomain findByEmail(String email);
}
