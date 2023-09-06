package br.com.gabrielferreira.usuario.dto.factory;

import br.com.gabrielferreira.usuario.dto.AnotacaoDTO;
import br.com.gabrielferreira.usuario.dto.AnotacaoResumidaDTO;
import br.com.gabrielferreira.usuario.entities.Anotacao;
import br.com.gabrielferreira.usuario.repository.projection.AnotacaoResumidaProjection;

import static br.com.gabrielferreira.usuario.dto.factory.UsuarioDTOFactory.*;

public class AnotacaoDTOFactory {

    private AnotacaoDTOFactory(){}

    public static AnotacaoDTO toAnotacaoDto(Anotacao anotacao){
        if(anotacao != null){
            return new AnotacaoDTO(anotacao.getId(), anotacao.getDescricao(), toUsuarioDto(anotacao.getUsuario()), anotacao.getCreatedAt(), anotacao.getUpdatedAt());
        }
        return null;
    }

    public static AnotacaoResumidaDTO toAnotacaoResumidaDto(AnotacaoResumidaProjection anotacaoResumidaProjection){
        if(anotacaoResumidaProjection != null){
            return new AnotacaoResumidaDTO(anotacaoResumidaProjection.getId(), anotacaoResumidaProjection.getDescricao(), anotacaoResumidaProjection.getCreatedAt(), anotacaoResumidaProjection.getUpdatedAt());
        }
        return null;
    }
}
