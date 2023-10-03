package br.com.gabrielferreira.usuario.mapper.dto;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.response.AnotacaoResponseDTO;
import br.com.gabrielferreira.usuario.dto.response.AnotacaoResumidaResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface AnotacaoDTOMapper {

    AnotacaoResponseDTO toAnotacaoDto(AnotacaoDomain anotacaoDomain);

    AnotacaoResumidaResponseDTO toAnotacaoResumidaDto(AnotacaoDomain anotacaoDomain);

    default Page<AnotacaoResumidaResponseDTO> toAnotacoesResumidaDtos(Page<AnotacaoDomain> anotacoesDomains){
        return anotacoesDomains.map(this::toAnotacaoResumidaDto);
    }
}
