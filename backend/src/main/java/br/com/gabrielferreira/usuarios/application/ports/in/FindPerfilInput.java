package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;

import java.util.List;

public interface FindPerfilInput {

    PerfilDomain findById(Long id);

    List<PerfilDomain> findAll();
}
