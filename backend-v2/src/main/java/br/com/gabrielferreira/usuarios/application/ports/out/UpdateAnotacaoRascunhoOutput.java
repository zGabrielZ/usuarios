package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface UpdateAnotacaoRascunhoOutput {

    void updateAnotacao(AnotacaoDomain anotacaoDomain);
}
