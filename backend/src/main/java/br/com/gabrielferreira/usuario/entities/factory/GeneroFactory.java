package br.com.gabrielferreira.usuario.entities.factory;

import br.com.gabrielferreira.usuario.dto.GeneroInsertDTO;
import br.com.gabrielferreira.usuario.entities.Genero;

public class GeneroFactory {

    private GeneroFactory(){}

    public static Genero toGenero(GeneroInsertDTO generoInsertDTO){
        if(generoInsertDTO != null){
            return Genero.builder()
                    .id(generoInsertDTO.getId())
                    .build();
        }
        return null;
    }
}
