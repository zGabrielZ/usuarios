package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;

import java.util.Optional;

public interface FindTelefoneOutput {

    Optional<TelefoneDomain> findByUsuarioId(Long idUsuario);

    Optional<TelefoneDomain> findByIdAndUsuarioId(Long id, Long idUsuario);
}
