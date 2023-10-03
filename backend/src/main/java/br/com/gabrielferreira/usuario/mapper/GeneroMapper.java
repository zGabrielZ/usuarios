package br.com.gabrielferreira.usuario.mapper;

import br.com.gabrielferreira.usuario.domain.GeneroDomain;
import br.com.gabrielferreira.usuario.dto.response.GeneroResponseDTO;
import br.com.gabrielferreira.usuario.entity.Genero;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroDomain toGeneroDomain(Genero genero);

    List<GeneroDomain> toGenerosDomains(List<Genero> generos);

    GeneroResponseDTO toGeneroResponseDto(GeneroDomain generoDomain);

    List<GeneroResponseDTO> toGenerosResponsesDtos(List<GeneroDomain> generoDomains);
}
