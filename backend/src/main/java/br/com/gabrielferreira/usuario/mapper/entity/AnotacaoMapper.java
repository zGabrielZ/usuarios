package br.com.gabrielferreira.usuario.mapper.entity;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnotacaoMapper {

    Anotacao toAnotacao(AnotacaoDomain anotacaoDomain);
}
