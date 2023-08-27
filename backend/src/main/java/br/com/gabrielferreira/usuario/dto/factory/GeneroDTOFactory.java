package br.com.gabrielferreira.usuario.dto.factory;

import br.com.gabrielferreira.usuario.dto.GeneroDTO;
import br.com.gabrielferreira.usuario.entities.Genero;

import java.util.List;

public class GeneroDTOFactory {

    private GeneroDTOFactory(){}

    public static List<GeneroDTO> toGenerosDtos(List<Genero> generos){
        return generos.stream().map(GeneroDTOFactory::toGeneroDto).toList();
    }

    public static GeneroDTO toGeneroDto(Genero genero){
        if(genero != null){
            return new GeneroDTO(genero.getId(), genero.getDescricao(), genero.getCodigo(), genero.getCreatedAt(), genero.getUpdatedAt());
        }
        return null;
    }
}
