package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.AnotacaoEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.AnotacaoRepository;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.specification.AnotacaoSpecification;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.ports.out.FindAnotacaoOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAnotacaoAdapter implements FindAnotacaoOutput {

    private final AnotacaoRepository anotacaoRepository;

    private final AnotacaoEntityMapper anotacaoEntityMapper;

    @Override
    public Optional<AnotacaoDomain> findByIdAndTipoAnotacaoAndIdUsuario(Long id, String tipoAnotacao, Long idUsuario) {
        Optional<AnotacaoEntity> anotacaoEntity = anotacaoRepository.findByIdAndIdUsuarioAndTipoAnotacao(id, idUsuario, tipoAnotacao);
        return anotacaoEntity.map(anotacaoEntityMapper::toAnotacaoDomain);
    }

    @Override
    public List<AnotacaoDomain> findAll(PageInfo pageInfo, String titulo, String descricao, Long idUsuario) {
        List<Sort.Order> orders = new ArrayList<>();
        pageInfo.getSortBy().forEach(sortBy -> orders.add(new Sort.Order(Sort.Direction.fromString(sortBy[0]), sortBy[1])));
        PageRequest pageRequest = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize(), Sort.by(orders));
        Page<AnotacaoEntity> anotacaoEntities = anotacaoRepository.findAll(new AnotacaoSpecification(titulo, descricao, idUsuario), pageRequest);
        return anotacaoEntityMapper.toAnotacoesDomains(anotacaoEntities);
    }
}
