package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.GeneroDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroDTO toGeneroDto(DominioDomain dominioDomain);

    default List<GeneroDTO> toGenerosDtos(List<DominioDomain> dominioDomains){
        return dominioDomains.stream().map(this::toGeneroDto).toList();
    }
}
