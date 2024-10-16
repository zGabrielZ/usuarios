package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.PerfilDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilMapper {

    PerfilDTO toPerfilDto(PerfilDomain perfilDomain);

    default List<PerfilDTO> toPerfisDtos(List<PerfilDomain> perfilDomains){
        return perfilDomains.stream().map(this::toPerfilDto).toList();
    }
}
