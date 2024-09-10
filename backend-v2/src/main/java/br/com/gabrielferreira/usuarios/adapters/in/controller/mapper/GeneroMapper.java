package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.GeneroDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroMapper INSTANCE = Mappers.getMapper(GeneroMapper.class);

    GeneroDTO toGeneroDto(DominioDomain dominioDomain);

    default List<GeneroDTO> toGenerosDtos(List<DominioDomain> dominioDomains){
        return dominioDomains.stream().map(this::toGeneroDto).toList();
    }
}
