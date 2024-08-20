package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;

public interface TelefoneMapperInput {

    TelefoneDomain update(TelefoneDomain telefoneDomain, DominioDomain tipoTelefoneDomain);
}
