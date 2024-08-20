package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AbstractObjetMapper.class)
public interface TelefoneMapper {

    @Mapping(target = "createdAt", qualifiedByName = "formatData")
    @Mapping(target = "updatedAt", qualifiedByName = "formatData")
    TelefoneDTO toTelefoneDto(TelefoneDomain telefoneDomain);

    @Mapping(target = "id", source = "id")
    TelefoneDomain createTelefoneDomain(TelefoneCreateDTO telefoneCreateDTO, Long id);
}
