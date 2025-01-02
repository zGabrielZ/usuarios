package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioUpdateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDomain createUsuarioDomain(UsuarioCreateDTO usuarioCreateDTO);

    UsuarioDTO toUsuarioDto(UsuarioDomain usuarioDomain);

    UsuarioResumidoDTO toUsuarioResumidoDto(UsuarioDomain usuarioDomain);

    @Mapping(target = "id", source = "id")
    UsuarioDomain updateUsuarioDomain(UsuarioUpdateDTO usuarioUpdateDTO, Long id);

    default List<UsuarioResumidoDTO> toUsuarioResumidoDtos(List<UsuarioDomain> usuarioDomains){
        return usuarioDomains.stream().map(this::toUsuarioResumidoDto).toList();
    }
}
