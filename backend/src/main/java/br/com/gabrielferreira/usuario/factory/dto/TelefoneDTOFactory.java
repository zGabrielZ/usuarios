package br.com.gabrielferreira.usuario.factory.dto;

import br.com.gabrielferreira.usuario.domain.TelefoneDomain;
import br.com.gabrielferreira.usuario.dto.response.TelefoneResponseDTO;

import static br.com.gabrielferreira.usuario.factory.dto.DominioDTOFactory.*;

public class TelefoneDTOFactory {

    private TelefoneDTOFactory(){}

    public static TelefoneResponseDTO toTelefoneDomain(TelefoneDomain telefoneDomain){
        if(telefoneDomain != null){
            return new TelefoneResponseDTO(telefoneDomain.getId(), telefoneDomain.getNumero(), telefoneDomain.getDdd(), telefoneDomain.getTelefoneFormatado(),
                    telefoneDomain.getDescricao(), toDominioResponseDto(telefoneDomain.getTipoTelefone()), telefoneDomain.getCreatedAt(),
                    telefoneDomain.getUpdatedAt());
        }
        return null;
    }
}
