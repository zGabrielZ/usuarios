package br.com.gabrielferreira.usuario.factory.dto;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.response.AnotacaoResponseDTO;
import org.springframework.data.domain.Page;

public class AnotacaoDTOFactory {

    private AnotacaoDTOFactory(){}

    public static AnotacaoResponseDTO toAnotacaoResponseDto(AnotacaoDomain anotacaoDomain){
        if(anotacaoDomain != null){
            return new AnotacaoResponseDTO(anotacaoDomain.getId(), anotacaoDomain.getTitulo(), anotacaoDomain.getDescricao(), anotacaoDomain.getCreatedAt(),
                    anotacaoDomain.getUpdatedAt());
        }
        return null;
    }

    public static Page<AnotacaoResponseDTO> toAnotacoesResponsesDtos(Page<AnotacaoDomain> anotacaoDomains){
        return anotacaoDomains.map(AnotacaoDTOFactory::toAnotacaoResponseDto);
    }
}
