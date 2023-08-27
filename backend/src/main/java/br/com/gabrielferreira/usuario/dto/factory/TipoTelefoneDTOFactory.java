package br.com.gabrielferreira.usuario.dto.factory;

import br.com.gabrielferreira.usuario.dto.TipoTelefoneDTO;
import br.com.gabrielferreira.usuario.entities.TipoTelefone;

import java.util.List;

public class TipoTelefoneDTOFactory {

    private TipoTelefoneDTOFactory(){}

    public static List<TipoTelefoneDTO> toTipoTelefonesDtos(List<TipoTelefone> tipoTelefones){
        return tipoTelefones.stream().map(TipoTelefoneDTOFactory::toTipoTelefoneDto).toList();
    }

    public static TipoTelefoneDTO toTipoTelefoneDto(TipoTelefone tipoTelefone){
        if(tipoTelefone != null){
            return new TipoTelefoneDTO(tipoTelefone.getId(), tipoTelefone.getDescricao(), tipoTelefone.getCodigo(), tipoTelefone.getCreatedAt(), tipoTelefone.getUpdatedAt());
        }
        return null;
    }
}
