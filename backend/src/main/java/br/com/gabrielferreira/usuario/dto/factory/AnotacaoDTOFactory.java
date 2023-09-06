package br.com.gabrielferreira.usuario.dto.factory;

import br.com.gabrielferreira.usuario.dto.AnotacaoDTO;
import br.com.gabrielferreira.usuario.entities.Anotacao;

import static br.com.gabrielferreira.usuario.dto.factory.UsuarioDTOFactory.*;

public class AnotacaoDTOFactory {

    private AnotacaoDTOFactory(){}

    public static AnotacaoDTO toAnotacaoDto(Anotacao anotacao){
        if(anotacao != null){
            return new AnotacaoDTO(anotacao.getId(), anotacao.getDescricao(), toUsuarioDto(anotacao.getUsuario()), anotacao.getCreatedAt(), anotacao.getUpdatedAt());
        }
        return null;
    }
}
