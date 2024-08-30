package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface CreateAnotacaoRascunhoOutput {

    AnotacaoDomain create(AnotacaoDomain anotacaoDomain);
}
