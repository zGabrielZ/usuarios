package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface UserCurrentInput {

    UsuarioDomain getUserCurrent();

    void validarAdminOuProprioUsuario(Long idUsuario);

    void validarAdminExclusao(Long idUsuario);
}
