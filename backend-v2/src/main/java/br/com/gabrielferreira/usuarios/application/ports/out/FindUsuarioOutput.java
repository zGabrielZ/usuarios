package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

import java.util.Optional;

public interface FindUsuarioOutput {

    Optional<UsuarioDomain> findByCpf(String cpf);

    Optional<UsuarioDomain> findByEmail(String email);

    Optional<UsuarioDomain> findById(Long id);
}
