package br.com.gabrielferreira.usuario.mapper.domain;

import br.com.gabrielferreira.usuario.domain.TipoTelefoneDomain;
import br.com.gabrielferreira.usuario.entity.TipoTelefone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoTelefoneDomainMapper {

    TipoTelefoneDomain toTipoTelefoneDomain(TipoTelefone tipoTelefone);

    List<TipoTelefoneDomain> toTipoTelefonesDomains(List<TipoTelefone> tipoTelefones);
}
