package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.UsuarioEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.UsuarioRepository;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.FindUsuarioOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindUsuarioAdapter implements FindUsuarioOutput {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public Optional<UsuarioDomain> findByCpf(String cpf) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findByCpf(cpf);
        return usuarioEntity.map(usuarioEntityMapper::toUsuarioDomain);
    }

    @Override
    public Optional<UsuarioDomain> findByEmail(String email) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findByEmail(email);
        return usuarioEntity.map(usuarioEntityMapper::toUsuarioDomain);
    }
}
