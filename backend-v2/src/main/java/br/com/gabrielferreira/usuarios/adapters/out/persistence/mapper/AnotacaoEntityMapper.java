package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnotacaoEntityMapper {

    AnotacaoEntity createAnotacaoEntity(AnotacaoDomain anotacaoDomain);

    AnotacaoDomain toAnotacaoDomain(AnotacaoEntity anotacaoEntity);
}
