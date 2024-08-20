package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TelefoneEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TelefoneEntityMapper {

    TelefoneDomain toTelefoneDomain(TelefoneEntity telefoneEntity);

    TelefoneEntity updateTelefoneEntity(TelefoneDomain telefoneDomain);

    @Mapping(target = "id", source = "telefoneDomainEncontrado.id")
    @Mapping(target = "numero", source = "telefoneDomain.numero")
    @Mapping(target = "ddd", source = "telefoneDomain.ddd")
    @Mapping(target = "descricao", source = "telefoneDomain.descricao")
    @Mapping(target = "tipoTelefone", source = "tipoTelefoneDomain")
    @Mapping(target = "createdAt", source = "telefoneDomainEncontrado.createdAt")
    @Mapping(target = "updatedAt", ignore = true)
    TelefoneDomain updateTelefone(TelefoneDomain telefoneDomain, TelefoneDomain telefoneDomainEncontrado, DominioDomain tipoTelefoneDomain);
}
