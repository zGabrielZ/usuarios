package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;

public interface TelefoneMapperOutput {

    TelefoneDomain update(TelefoneDomain telefoneDomain, TelefoneDomain telefoneDomainEncontrado, DominioDomain tipoTelefoneDomain);
}
