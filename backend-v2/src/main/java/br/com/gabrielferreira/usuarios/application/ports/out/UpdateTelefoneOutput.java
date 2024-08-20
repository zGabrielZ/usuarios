package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;

public interface UpdateTelefoneOutput {

    TelefoneDomain update(TelefoneDomain telefoneDomain);
}
