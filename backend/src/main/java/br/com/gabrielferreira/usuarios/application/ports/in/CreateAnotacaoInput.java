package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface CreateAnotacaoInput {

    AnotacaoDomain createRascunho(AnotacaoDomain anotacaoDomain, Long idUsuario);

    AnotacaoDomain createEstudo(AnotacaoDomain anotacaoDomain, Long idUsuario);

    AnotacaoDomain createLembrete(AnotacaoDomain anotacaoDomain, Long idUsuario);
}
