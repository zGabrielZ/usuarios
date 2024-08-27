package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoRascunhoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AbstractObjetMapper.class})
public interface AnotacaoMapper {

    AnotacaoDomain createAnotacaoDomain(AnotacaoRascunhoCreateDTO anotacaoRascunhoCreateDTO);

    @Mapping(target = "createdAt", qualifiedByName = "formatData")
    @Mapping(target = "updatedAt", qualifiedByName = "formatData")
    AnotacaoRascunhoDTO toAnotacaoRascunhoDto(AnotacaoDomain anotacaoDomain);
}
