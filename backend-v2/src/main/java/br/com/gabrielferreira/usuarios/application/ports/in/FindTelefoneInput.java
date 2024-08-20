package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;

public interface FindTelefoneInput {

    TelefoneDomain findByUsuarioId(Long idUsuario);
}
