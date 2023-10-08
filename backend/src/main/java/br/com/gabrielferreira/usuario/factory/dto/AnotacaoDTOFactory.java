package br.com.gabrielferreira.usuario.factory.dto;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.response.AnotacaoResponseDTO;
import org.springframework.data.domain.Page;

import static br.com.gabrielferreira.usuario.factory.dto.DominioDTOFactory.*;

public class AnotacaoDTOFactory {

    private AnotacaoDTOFactory(){}

    public static AnotacaoResponseDTO toAnotacaoResponseDto(AnotacaoDomain anotacaoDomain){
        if(anotacaoDomain != null){
            return new AnotacaoResponseDTO(anotacaoDomain.getId(), anotacaoDomain.getTitulo(), anotacaoDomain.getDescricao(),
                    toDominioResponseDto(anotacaoDomain.getTipoAnotacao()), anotacaoDomain.getDataLembrete(), anotacaoDomain.getDataEstudoInicio(),
                    anotacaoDomain.getDataEstudoFim(), toDominioResponseDto(anotacaoDomain.getSituacaoTipoAnotacao()), anotacaoDomain.getCreatedAt(),
                    anotacaoDomain.getUpdatedAt());
        }
        return null;
    }

    public static Page<AnotacaoResponseDTO> toAnotacoesResponsesDtos(Page<AnotacaoDomain> anotacaoDomains){
        return anotacaoDomains.map(AnotacaoDTOFactory::toAnotacaoResponseDto);
    }
}
