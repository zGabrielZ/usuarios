package br.com.gabrielferreira.usuario.mapper.dto;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.response.UsuarioResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {

    UsuarioResponseDTO toUsuarioDto(UsuarioDomain usuarioDomain);

    default Page<UsuarioResponseDTO> toUsuariosDtos(Page<UsuarioDomain> usuarioDomains){
        return usuarioDomains.map(this::toUsuarioDto);
    }
}
