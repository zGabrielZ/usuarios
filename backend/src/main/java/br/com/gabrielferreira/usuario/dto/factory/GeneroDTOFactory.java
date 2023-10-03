package br.com.gabrielferreira.usuario.dto.factory;

import br.com.gabrielferreira.usuario.dto.response.GeneroResponseDTO;
import br.com.gabrielferreira.usuario.entity.Genero;

import java.util.List;

public class GeneroDTOFactory {

    private GeneroDTOFactory(){}

    public static List<GeneroResponseDTO> toGenerosDtos(List<Genero> generos){
        return generos.stream().map(GeneroDTOFactory::toGeneroDto).toList();
    }

    public static GeneroResponseDTO toGeneroDto(Genero genero){
        if(genero != null){
            return new GeneroResponseDTO(genero.getId(), genero.getDescricao(), genero.getCodigo());
        }
        return null;
    }
}
