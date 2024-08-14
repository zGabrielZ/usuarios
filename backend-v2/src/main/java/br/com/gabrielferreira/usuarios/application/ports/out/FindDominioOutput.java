package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;

import java.util.Optional;

public interface FindDominioOutput {

    Optional<DominioDomain> findByIdAndTipoCodigo(Long id, String codigo);
}
