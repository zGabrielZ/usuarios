package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface FindAnotacaoRascunhoInput {

    AnotacaoDomain findByIdTipoAnotacaoRascunho(Long id, Long idUsuario);
}
