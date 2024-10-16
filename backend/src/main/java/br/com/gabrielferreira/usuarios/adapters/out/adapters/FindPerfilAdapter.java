package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.PerfilEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.PerfilEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.PerfilRepository;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.FindPerfilOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindPerfilAdapter implements FindPerfilOutput {

    private final PerfilRepository perfilRepository;

    private final PerfilEntityMapper perfilEntityMapper;

    @Cacheable(unless = "#result == null", value = "findById", key = "T(java.lang.String).format('%s_%s_%s', #root.target.Class.simpleName, #root.methodName, #id)")
    @Override
    public Optional<PerfilDomain> findById(Long id) {
        Optional<PerfilEntity> perfilEntityOptional = perfilRepository.findById(id);
        return perfilEntityOptional.map(perfilEntityMapper::toPerfilDomain);
    }

    @Cacheable(unless = "#result == null", value = "findAll", key = "T(java.lang.String).format('%s_%s', #root.target.Class.simpleName, #root.methodName)")
    @Override
    public List<PerfilDomain> findAll() {
        List<PerfilEntity> perfis = perfilRepository.findAllByOrderByTitulo();
        return perfilEntityMapper.toPerfisDomains(perfis);
    }

    @Cacheable(unless = "#result == null", value = "findByRole", key = "T(java.lang.String).format('%s_%s_%s', #root.target.Class.simpleName, #root.methodName, #role)")
    @Override
    public Optional<PerfilDomain> findByRole(String role) {
        Optional<PerfilEntity> perfilEntityOptional = perfilRepository.findByAutoriedade(role);
        return perfilEntityOptional.map(perfilEntityMapper::toPerfilDomain);
    }
}
