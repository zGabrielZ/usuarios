package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;

public interface FindGeneroInput {

    DominioDomain findById(Long id);
}
