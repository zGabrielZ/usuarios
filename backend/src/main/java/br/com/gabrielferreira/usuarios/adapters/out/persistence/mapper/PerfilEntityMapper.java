package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.PerfilEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilEntityMapper {

    PerfilDomain toPerfilDomain(PerfilEntity perfilEntity);

    default List<PerfilDomain> toPerfisDomains(List<PerfilEntity> perfis){
        List<PerfilDomain> perfilDomains = new ArrayList<>();
        perfis.forEach(perfilEntity -> perfilDomains.add(toPerfilDomain(perfilEntity)));
        return perfilDomains;
    }
}
