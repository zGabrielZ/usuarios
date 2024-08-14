package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.DominioEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.DominioEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.DominioRepository;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.FindDominioOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindDominioAdapter implements FindDominioOutput {

    private final DominioRepository dominioRepository;

    private final DominioEntityMapper dominioEntityMapper;

    @Override
    public Optional<DominioDomain> findByIdAndTipoCodigo(Long id, String codigo) {
        Optional<DominioEntity> dominioEntity = dominioRepository.findByIdAndTipoCodigo(id, codigo);
        return dominioEntity.map(dominioEntityMapper::toDominioDomain);
    }
}
