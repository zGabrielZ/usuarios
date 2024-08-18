package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;

import java.util.List;
import java.util.Optional;

public interface FindDominioOutput {

    Optional<DominioDomain> findByIdAndTipoCodigo(Long id, String codigo);

    List<DominioDomain> findAllByTipoCodigo(String codigo);
}
