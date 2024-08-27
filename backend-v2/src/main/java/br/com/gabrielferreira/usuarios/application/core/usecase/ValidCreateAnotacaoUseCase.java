package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateAnotacaoInput;

public class ValidCreateAnotacaoUseCase implements ValidCreateAnotacaoInput {

    @Override
    public void validarCampos(AnotacaoDomain anotacaoDomain) {
        anotacaoDomain.setTitulo(anotacaoDomain.getTitulo().trim());
        anotacaoDomain.setDescricao(anotacaoDomain.getDescricao().trim());
    }
}
