package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoEstudoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoLembreteCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoEstudoDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoLembreteDTO;
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

    AnotacaoDomain createAnotacaoDomain(AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO);

    @Mapping(target = "createdAt", qualifiedByName = "formatData")
    @Mapping(target = "updatedAt", qualifiedByName = "formatData")
    @Mapping(target = "dataLembrete", qualifiedByName = "formatData")
    AnotacaoLembreteDTO toAnotacaoLembreteDto(AnotacaoDomain anotacaoDomain);

    AnotacaoDomain createAnotacaoDomain(AnotacaoEstudoCreateDTO anotacaoEstudoCreateDTO);

    @Mapping(target = "createdAt", qualifiedByName = "formatData")
    @Mapping(target = "updatedAt", qualifiedByName = "formatData")
    @Mapping(target = "dataEstudoInicio", qualifiedByName = "formatData")
    @Mapping(target = "dataEstudoFim", qualifiedByName = "formatData")
    AnotacaoEstudoDTO toAnotacaoEstudoDto(AnotacaoDomain anotacaoDomain);
}
