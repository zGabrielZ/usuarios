package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface CreateUsuarioOutput {

    UsuarioDomain create(UsuarioDomain usuarioDomain);
}
