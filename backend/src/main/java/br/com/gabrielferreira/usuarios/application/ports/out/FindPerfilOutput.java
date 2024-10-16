package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;

import java.util.List;
import java.util.Optional;

public interface FindPerfilOutput {

    Optional<PerfilDomain> findById(Long id);

    List<PerfilDomain> findAll();
}
