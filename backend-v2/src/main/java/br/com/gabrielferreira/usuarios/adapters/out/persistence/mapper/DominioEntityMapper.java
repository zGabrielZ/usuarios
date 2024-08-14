package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.DominioEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DominioEntityMapper {

    DominioDomain toDominioDomain(DominioEntity dominioEntity);
}
