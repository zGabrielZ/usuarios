package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TelefoneEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelefoneEntityMapper {

    TelefoneDomain toTelefoneDomain(TelefoneEntity telefoneEntity);

    TelefoneEntity updateTelefoneEntity(TelefoneDomain telefoneDomain);

    default TelefoneDomain updateTelefone(TelefoneDomain telefoneDomain, TelefoneDomain telefoneDomainEncontrado, DominioDomain tipoTelefoneDomain){
        telefoneDomainEncontrado.setNumero(telefoneDomain.getNumero());
        telefoneDomainEncontrado.setDdd(telefoneDomain.getDdd());
        telefoneDomainEncontrado.setDescricao(telefoneDomain.getDescricao());
        telefoneDomainEncontrado.setTipoTelefone(tipoTelefoneDomain);
        return telefoneDomainEncontrado;
    }
}
