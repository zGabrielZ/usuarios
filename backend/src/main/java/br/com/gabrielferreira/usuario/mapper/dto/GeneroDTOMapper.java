package br.com.gabrielferreira.usuario.mapper.dto;

import br.com.gabrielferreira.usuario.domain.GeneroDomain;
import br.com.gabrielferreira.usuario.dto.response.GeneroResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroDTOMapper {

    GeneroResponseDTO toGeneroDto(GeneroDomain generoDomain);

    List<GeneroResponseDTO> toGenerosDtos(List<GeneroDomain> generoDomains);
}
