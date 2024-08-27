package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface ValidCreateAnotacaoInput {

    void validarCampos(AnotacaoDomain anotacaoDomain);
}
