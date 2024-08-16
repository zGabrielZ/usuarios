package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;

public interface FindTipoTelefoneInput {

    DominioDomain findById(Long id);
}
