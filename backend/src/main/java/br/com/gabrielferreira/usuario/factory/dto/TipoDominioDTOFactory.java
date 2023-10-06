package br.com.gabrielferreira.usuario.factory.dto;

import br.com.gabrielferreira.usuario.domain.TipoDominioDomain;
import br.com.gabrielferreira.usuario.dto.response.TipoDominioResponseDTO;
import java.util.List;

public class TipoDominioDTOFactory {

    private TipoDominioDTOFactory(){}

    public static TipoDominioResponseDTO toTipoDominioResponseDto(TipoDominioDomain tipoDominioDomain){
        if(tipoDominioDomain != null){
            return new TipoDominioResponseDTO(tipoDominioDomain.getId(), tipoDominioDomain.getDescricao(), tipoDominioDomain.getCodigo());
        }
        return null;
    }

    public static List<TipoDominioResponseDTO> toTiposDominiosResponsesDtos(List<TipoDominioDomain> tipoDominioDomains){
        return tipoDominioDomains.stream().map(TipoDominioDTOFactory::toTipoDominioResponseDto).toList();
    }
}
