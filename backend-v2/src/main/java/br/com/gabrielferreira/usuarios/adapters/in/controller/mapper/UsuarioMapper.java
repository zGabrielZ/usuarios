package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AbstractObjetMapper.class})
public interface UsuarioMapper {

    UsuarioDomain createUsuarioDomain(UsuarioCreateDTO usuarioCreateDTO);

    @Mapping(target = "createdAt", qualifiedByName = "formatData")
    @Mapping(target = "updatedAt", qualifiedByName = "formatData")
    @Mapping(target = "telefone.createdAt", qualifiedByName = "formatData")
    @Mapping(target = "telefone.updatedAt", qualifiedByName = "formatData")
    UsuarioDTO toUsuarioDto(UsuarioDomain usuarioDomain);

    @Mapping(target = "createdAt", qualifiedByName = "formatData")
    @Mapping(target = "updatedAt", qualifiedByName = "formatData")
    UsuarioResumidoDTO toUsuarioResumidoDto(UsuarioDomain usuarioDomain);
}
