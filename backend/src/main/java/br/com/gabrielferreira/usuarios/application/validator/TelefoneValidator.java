package br.com.gabrielferreira.usuarios.application.validator;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;

public interface TelefoneValidator {

    void validarCampos(TelefoneDomain telefoneDomain);

    void validarNumeroComTipoTelefone(TelefoneDomain telefoneDomain, DominioDomain tipoTelefone);
}
