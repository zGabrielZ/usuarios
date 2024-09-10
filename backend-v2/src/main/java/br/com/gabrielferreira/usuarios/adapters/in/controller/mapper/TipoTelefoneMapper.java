package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TipoTelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoTelefoneMapper {

    TipoTelefoneMapper INSTANCE = Mappers.getMapper(TipoTelefoneMapper.class);

    TipoTelefoneDTO toTipoTelefoneDto(DominioDomain dominioDomain);

    default List<TipoTelefoneDTO> toTiposTelefonesDtos(List<DominioDomain> dominioDomains){
        return dominioDomains.stream().map(this::toTipoTelefoneDto).toList();
    }
}
