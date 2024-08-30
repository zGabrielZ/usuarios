package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnotacaoEntityMapper {

    AnotacaoEntity createAnotacaoEntity(AnotacaoDomain anotacaoDomain);

    @Mapping(target = "usuario.telefone", ignore = true)
    @Mapping(target = "usuario.genero", ignore = true)
    @Mapping(target = "usuario.anotacoes", ignore = true)
    AnotacaoDomain toAnotacaoDomain(AnotacaoEntity anotacaoEntity);
}
