package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.UsuarioEntityMapper;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.UsuarioMapperOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapperAdapter implements UsuarioMapperOutput {

    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public UsuarioDomain createUsuarioDomain(UsuarioDomain usuarioDomain, DominioDomain generoDomain, DominioDomain tipoTelefoneDomain) {
        return usuarioEntityMapper.createUsuarioDomain(usuarioDomain, generoDomain, tipoTelefoneDomain);
    }

    @Override
    public UsuarioDomain update(UsuarioDomain usuarioDomain, UsuarioDomain usuarioDomainEncontrado, DominioDomain generoDomain) {
        return usuarioEntityMapper.updateUsuario(usuarioDomain, usuarioDomainEncontrado, generoDomain);
    }
}
