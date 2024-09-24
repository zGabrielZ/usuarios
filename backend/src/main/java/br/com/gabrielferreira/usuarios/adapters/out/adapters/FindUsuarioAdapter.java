package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.UsuarioEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.UsuarioRepository;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.specification.UsuarioSpecification;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.FindUsuarioOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindUsuarioAdapter implements FindUsuarioOutput {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public Optional<UsuarioDomain> findByCpf(String cpf) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findByCpf(cpf);
        return usuarioEntity.map(usuarioEntityMapper::toOnlyUsuarioDomain);
    }

    @Override
    public Optional<UsuarioDomain> findByEmail(String email) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findByEmail(email);
        return usuarioEntity.map(usuarioEntityMapper::toOnlyUsuarioDomain);
    }

    @Override
    public Optional<UsuarioDomain> findById(Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findUsuarioById(id);
        return usuarioEntity.map(usuarioEntityMapper::toUsuarioDomain);
    }

    @Override
    public List<UsuarioDomain> findAll(PageInfo pageInfo, String nome, String email, BigDecimal renda) {
        List<Sort.Order> orders = new ArrayList<>();
        pageInfo.getSortBy().forEach(sortBy -> orders.add(new Sort.Order(Sort.Direction.fromString(sortBy[0]), sortBy[1])));
        PageRequest pageRequest = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize(), Sort.by(orders));
        Page<UsuarioEntity> usuarioEntities = usuarioRepository.findAll(new UsuarioSpecification(nome, email, renda), pageRequest);
        return usuarioEntityMapper.toUsuariosDomains(usuarioEntities);
    }
}
