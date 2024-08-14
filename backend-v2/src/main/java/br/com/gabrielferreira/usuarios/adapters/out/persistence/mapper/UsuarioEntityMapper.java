package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntity toUsuarioEntity(UsuarioDomain usuarioDomain);

    UsuarioDomain toUsuarioDomain(UsuarioEntity usuarioEntity);
}
