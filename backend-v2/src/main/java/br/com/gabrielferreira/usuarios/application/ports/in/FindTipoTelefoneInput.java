package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;

import java.util.List;

public interface FindTipoTelefoneInput {

    DominioDomain findById(Long id);

    List<DominioDomain> findAllByTipoCodigo();
}
