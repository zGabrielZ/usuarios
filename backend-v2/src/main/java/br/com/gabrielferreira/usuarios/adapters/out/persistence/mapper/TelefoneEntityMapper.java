package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TelefoneEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelefoneEntityMapper {

    TelefoneDomain toTelefoneDomain(TelefoneEntity telefoneEntity);
}
