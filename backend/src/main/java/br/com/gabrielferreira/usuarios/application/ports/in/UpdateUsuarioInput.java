package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface UpdateUsuarioInput {

    UsuarioDomain update(UsuarioDomain usuarioDomain);

    void updateRoleAdmin(Long id);

    void updateRoleClient(Long id);
}
