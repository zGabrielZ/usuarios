package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.UsuarioEntityMapper;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.UsuarioRepository;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateUsuarioOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateUsuarioAdapter implements UpdateUsuarioOutput {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioEntityMapper usuarioEntityMapper;

    @Transactional
    @Override
    public UsuarioDomain update(UsuarioDomain usuarioDomain) {
        UsuarioEntity usuarioEntity = usuarioEntityMapper.updateUsuarioEntity(usuarioDomain);
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return usuarioEntityMapper.toUsuarioDomain(usuarioEntity);
    }
}
