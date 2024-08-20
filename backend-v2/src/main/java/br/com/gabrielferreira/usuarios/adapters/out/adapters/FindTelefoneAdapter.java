package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TelefoneEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.TelefoneEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.TelefoneRepository;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.FindTelefoneOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindTelefoneAdapter implements FindTelefoneOutput {

    private final TelefoneRepository telefoneRepository;

    private final TelefoneEntityMapper telefoneEntityMapper;

    @Override
    public Optional<TelefoneDomain> findByUsuarioId(Long idUsuario) {
        Optional<TelefoneEntity> telefoneEntity = telefoneRepository.findByUsuarioId(idUsuario);
        return telefoneEntity.map(telefoneEntityMapper::toTelefoneDomain);
    }
}
