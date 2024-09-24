package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.TelefoneEntityMapper;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.TelefoneMapperOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelefoneMapperAdapter implements TelefoneMapperOutput {

    private final TelefoneEntityMapper telefoneEntityMapper;

    @Override
    public TelefoneDomain update(TelefoneDomain telefoneDomain, TelefoneDomain telefoneDomainEncontrado, DominioDomain tipoTelefoneDomain) {
        return telefoneEntityMapper.updateTelefone(telefoneDomain, telefoneDomainEncontrado, tipoTelefoneDomain);
    }
}
