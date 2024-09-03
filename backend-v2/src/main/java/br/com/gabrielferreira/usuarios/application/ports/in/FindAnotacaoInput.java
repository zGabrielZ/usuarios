package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface FindAnotacaoInput {

    AnotacaoDomain findByIdTipoAnotacaoRascunho(Long id, Long idUsuario);

    AnotacaoDomain findByIdTipoAnotacaoEstudo(Long id, Long idUsuario);

    AnotacaoDomain findByIdTipoAnotacaoLembrete(Long id, Long idUsuario);
}
