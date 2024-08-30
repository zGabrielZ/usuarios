package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.AnotacaoEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.AnotacaoRepository;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.FindAnotacaoRascunhoOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAnotacaoRascunhoAdapter implements FindAnotacaoRascunhoOutput {

    private final AnotacaoRepository anotacaoRepository;

    private final AnotacaoEntityMapper anotacaoEntityMapper;

    @Override
    public Optional<AnotacaoDomain> findByIdAndTipoAnotacaoAndIdUsuario(Long id, String tipoAnotacao, Long idUsuario) {
        Optional<AnotacaoEntity> anotacaoEntity = anotacaoRepository.findByIdAndIdUsuarioAndTipoAnotacao(id, idUsuario, tipoAnotacao);
        return anotacaoEntity.map(anotacaoEntityMapper::toAnotacaoDomain);
    }
}
