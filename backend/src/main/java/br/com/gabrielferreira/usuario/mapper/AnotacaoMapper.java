package br.com.gabrielferreira.usuario.mapper;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoUpdateRequestDTO;
import br.com.gabrielferreira.usuario.dto.response.AnotacaoResponseDTO;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface AnotacaoMapper {

    Anotacao toAnotacao(AnotacaoDomain anotacaoDomain);

    @Mapping(target = "usuario", ignore = true)
    AnotacaoDomain toAnotacaoDomainWithoutUsuario(Anotacao anotacao);

    @Mapping(target = "usuario.anotacoes", ignore = true)
    @Mapping(target = "usuario.telefone", ignore = true)
    @Mapping(target = "usuario.genero", ignore = true)
    AnotacaoDomain toAnotacaoDomainWithUsuario(Anotacao anotacao);

    AnotacaoDomain toAnotacaoDomain(AnotacaoCreateRequestDTO anotacaoCreateRequestDTO);

    AnotacaoResponseDTO toAnotacaoResponseDto(AnotacaoDomain anotacaoDomain);

    AnotacaoDomain toAnotacaoDomain(AnotacaoUpdateRequestDTO anotacaoUpdateRequestDTO);

    default AnotacaoDomain toAnotacaoDomain(Long id, AnotacaoUpdateRequestDTO anotacaoUpdateRequestDTO){
        AnotacaoDomain anotacaoDomain = toAnotacaoDomain(anotacaoUpdateRequestDTO);
        anotacaoDomain.setId(id);
        return anotacaoDomain;
    }

    default Page<AnotacaoDomain> toAnotacoesDomains(Page<Anotacao> anotacoes){
        return anotacoes.map(this::toAnotacaoDomainWithoutUsuario);
    }

    default Page<AnotacaoResponseDTO> toAnotacoesResponsesDtos(Page<AnotacaoDomain> anotacaoDomains){
        return anotacaoDomains.map(this::toAnotacaoResponseDto);
    }

}
