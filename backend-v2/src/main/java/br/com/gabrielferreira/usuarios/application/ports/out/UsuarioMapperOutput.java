package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface UsuarioMapperOutput {

    UsuarioDomain update(UsuarioDomain usuarioDomain, UsuarioDomain usuarioDomainEncontrado, DominioDomain generoDomain);
}
