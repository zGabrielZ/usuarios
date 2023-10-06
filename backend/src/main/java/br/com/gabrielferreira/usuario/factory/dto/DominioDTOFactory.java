package br.com.gabrielferreira.usuario.factory.dto;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.dto.response.DominioResponseDTO;

import java.util.List;

import static br.com.gabrielferreira.usuario.factory.dto.TipoDominioDTOFactory.*;

public class DominioDTOFactory {

    private DominioDTOFactory(){}

    public static DominioResponseDTO toDominioResponseDto(DominioDomain dominioDomain){
        if(dominioDomain != null){
            return new DominioResponseDTO(dominioDomain.getId(), dominioDomain.getDescricao(), dominioDomain.getCodigo(), toTipoDominioResponseDto(dominioDomain.getTipo()));
        }
        return null;
    }

    public static List<DominioResponseDTO> toDominiosResponsesDtos(List<DominioDomain> dominioDomains){
        return dominioDomains.stream().map(DominioDTOFactory::toDominioResponseDto).toList();
    }
}
