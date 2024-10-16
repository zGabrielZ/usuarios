package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface UsuarioMapperOutput {

    UsuarioDomain createUsuarioDomain(UsuarioDomain usuarioDomain, DominioDomain generoDomain, DominioDomain tipoTelefoneDomain, PerfilDomain perfilDomain);

    UsuarioDomain update(UsuarioDomain usuarioDomain, UsuarioDomain usuarioDomainEncontrado, DominioDomain generoDomain);
}
