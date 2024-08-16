package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    @Mapping(source = "usuarioDomain.anotacoes", target = "anotacoes", ignore = true)
    UsuarioEntity createUsuarioEntity(UsuarioDomain usuarioDomain);

    @Mapping(source = "usuarioEntity.anotacoes", target = "anotacoes", ignore = true)
    UsuarioDomain toUsuarioDomain(UsuarioEntity usuarioEntity);

    @Mapping(source = "usuarioEntity.telefone", target = "telefone", ignore = true)
    @Mapping(source = "usuarioEntity.genero", target = "genero", ignore = true)
    @Mapping(source = "usuarioEntity.anotacoes", target = "anotacoes", ignore = true)
    UsuarioDomain toOnlyUsuarioDomain(UsuarioEntity usuarioEntity);
}
