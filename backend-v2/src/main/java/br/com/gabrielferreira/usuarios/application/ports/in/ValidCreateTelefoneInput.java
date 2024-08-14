package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;

public interface ValidCreateTelefoneInput {

    void validarCampos(TelefoneDomain telefoneDomain);

    void validarTipoTelefoneExistente(DominioDomain tipoTelefone);

    void validarNumeroComTipoTelefone(TelefoneDomain telefoneDomain, DominioDomain tipoTelefone);
}
