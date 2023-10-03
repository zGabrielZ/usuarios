package br.com.gabrielferreira.usuario.mapper.domain;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoUpdateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import br.com.gabrielferreira.usuario.repository.projection.AnotacaoResumidaProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface AnotacaoDomainMapper {

    AnotacaoDomain toAnotacaoDomain(AnotacaoCreateRequestDTO anotacaoCreateRequestDTO);

    AnotacaoDomain toAnotacaoDomain(AnotacaoUpdateRequestDTO anotacaoUpdateRequestDTO);

    @Mapping(target = "usuario.anotacoes", ignore = true)
    AnotacaoDomain toAnotacaoDomain(Anotacao anotacao);

    AnotacaoDomain toAnotacaoDomain(AnotacaoResumidaProjection anotacaoResumidaProjection);

    default AnotacaoDomain toAnotacaoDomain(Long id, AnotacaoUpdateRequestDTO anotacaoUpdateRequestDTO){
        AnotacaoDomain anotacaoDomain = toAnotacaoDomain(anotacaoUpdateRequestDTO);
        anotacaoDomain.setId(id);
        return anotacaoDomain;
    }

    default Page<AnotacaoDomain> toAnotacoesDomains(Page<Anotacao> anotacoes){
        return anotacoes.map(this::toAnotacaoDomain);
    }

    default void updateAnotacaoDomain(AnotacaoDomain anotacaoDomainEncontrado, AnotacaoDomain anotacaoDomainUpdate){
        if(anotacaoDomainEncontrado != null && anotacaoDomainUpdate != null){
            anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
            anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        }
    }
}
