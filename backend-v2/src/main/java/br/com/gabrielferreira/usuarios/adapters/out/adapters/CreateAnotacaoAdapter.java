package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.AnotacaoEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.AnotacaoRepository;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateAnotacaoOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateAnotacaoAdapter implements CreateAnotacaoOutput {

    private final AnotacaoRepository anotacaoRepository;

    private final AnotacaoEntityMapper anotacaoEntityMapper;

    @Transactional
    @Override
    public AnotacaoDomain create(AnotacaoDomain anotacaoDomain) {
        AnotacaoEntity anotacaoEntity = anotacaoEntityMapper.createAnotacaoEntity(anotacaoDomain);
        anotacaoEntity = anotacaoRepository.save(anotacaoEntity);
        return anotacaoEntityMapper.toAnotacaoDomain(anotacaoEntity);
    }
}
