package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoEstudoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoLembreteCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoEstudoDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoLembreteDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoRascunhoDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnotacaoMapper {

    AnotacaoDomain createAnotacaoDomain(AnotacaoRascunhoCreateDTO anotacaoRascunhoCreateDTO);

    AnotacaoRascunhoDTO toAnotacaoRascunhoDto(AnotacaoDomain anotacaoDomain);

    AnotacaoDomain createAnotacaoDomain(AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO);

    AnotacaoLembreteDTO toAnotacaoLembreteDto(AnotacaoDomain anotacaoDomain);

    AnotacaoDomain createAnotacaoDomain(AnotacaoEstudoCreateDTO anotacaoEstudoCreateDTO);

    AnotacaoEstudoDTO toAnotacaoEstudoDto(AnotacaoDomain anotacaoDomain);

    AnotacaoResumidoDTO toAnotacaoResumitoDto(AnotacaoDomain anotacaoDomain);

    default List<AnotacaoResumidoDTO> toAnotacoesResumidosDtos(List<AnotacaoDomain> anotacaoDomains){
        return anotacaoDomains.stream().map(this::toAnotacaoResumitoDto).toList();
    }
}
