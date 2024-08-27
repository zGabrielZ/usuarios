package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface CreateAnotacaoRascunhoInput {

    AnotacaoDomain create(AnotacaoDomain anotacaoDomain, Long idUsuario);
}
