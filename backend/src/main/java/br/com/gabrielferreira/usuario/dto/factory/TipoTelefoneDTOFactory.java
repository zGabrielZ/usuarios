package br.com.gabrielferreira.usuario.dto.factory;

import br.com.gabrielferreira.usuario.dto.response.TipoTelefoneResponseDTO;
import br.com.gabrielferreira.usuario.entity.TipoTelefone;

import java.util.List;

public class TipoTelefoneDTOFactory {

    private TipoTelefoneDTOFactory(){}

    public static List<TipoTelefoneResponseDTO> toTipoTelefonesDtos(List<TipoTelefone> tipoTelefones){
        return tipoTelefones.stream().map(TipoTelefoneDTOFactory::toTipoTelefoneDto).toList();
    }

    public static TipoTelefoneResponseDTO toTipoTelefoneDto(TipoTelefone tipoTelefone){
        if(tipoTelefone != null){
            return new TipoTelefoneResponseDTO(tipoTelefone.getId(), tipoTelefone.getDescricao(), tipoTelefone.getCodigo());
        }
        return null;
    }
}
