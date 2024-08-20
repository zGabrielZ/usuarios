package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TelefoneEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.TelefoneEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.TelefoneRepository;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateTelefoneOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateTelefoneAdapter implements UpdateTelefoneOutput {

    private final TelefoneRepository telefoneRepository;

    private final TelefoneEntityMapper telefoneEntityMapper;

    @Transactional
    @Override
    public TelefoneDomain update(TelefoneDomain telefoneDomain) {
        TelefoneEntity telefoneEntity = telefoneEntityMapper.updateTelefoneEntity(telefoneDomain);
        telefoneEntity = telefoneRepository.save(telefoneEntity);
        return telefoneEntityMapper.toTelefoneDomain(telefoneEntity);
    }
}
