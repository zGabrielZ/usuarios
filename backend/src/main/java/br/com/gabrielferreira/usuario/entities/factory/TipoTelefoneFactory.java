package br.com.gabrielferreira.usuario.entities.factory;

import br.com.gabrielferreira.usuario.dto.TipoTelefoneInsertDTO;
import br.com.gabrielferreira.usuario.entities.TipoTelefone;

public class TipoTelefoneFactory {

    private TipoTelefoneFactory(){}

    public static TipoTelefone toTipoTelefone(TipoTelefoneInsertDTO tipoTelefoneInsertDTO){
        if(tipoTelefoneInsertDTO != null){
            return TipoTelefone.builder()
                    .id(tipoTelefoneInsertDTO.getId())
                    .build();
        }
        return null;
    }
}
