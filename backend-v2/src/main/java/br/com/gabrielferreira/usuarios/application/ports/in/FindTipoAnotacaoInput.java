package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;

public interface FindTipoAnotacaoInput {

    DominioDomain findByCodigo(String codigo);
}
