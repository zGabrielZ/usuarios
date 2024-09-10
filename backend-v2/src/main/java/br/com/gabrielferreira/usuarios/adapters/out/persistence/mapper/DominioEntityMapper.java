package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.DominioEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DominioEntityMapper {

    DominioEntityMapper INSTANCE = Mappers.getMapper(DominioEntityMapper.class);

    DominioDomain toDominioDomain(DominioEntity dominioEntity);

    default List<DominioDomain> toDominiosDomains(List<DominioEntity> dominioEntities){
        List<DominioDomain> dominioDomains = new ArrayList<>();
        dominioEntities.forEach(dominioEntity -> dominioDomains.add(toDominioDomain(dominioEntity)));
        return dominioDomains;
    }
}
