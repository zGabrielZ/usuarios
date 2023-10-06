package br.com.gabrielferreira.usuario.factory.entity;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.entity.Anotacao;

import static br.com.gabrielferreira.usuario.factory.entity.UsuarioFactory.*;

public class AnotacaoFactory {

    private AnotacaoFactory(){}

    public static Anotacao toCreateAnotacao(AnotacaoDomain anotacaoDomain){
        if(anotacaoDomain != null){
            return Anotacao.builder()
                    .titulo(anotacaoDomain.getTitulo())
                    .descricao(anotacaoDomain.getDescricao())
                    .usuario(toUsuario(anotacaoDomain.getUsuario()))
                    .build();
        }
        return null;
    }

    public static Anotacao toUpdateAnotacao(AnotacaoDomain anotacaoDomainEncontrado, AnotacaoDomain anotacaoDomainUpdate){
        if(anotacaoDomainEncontrado != null && anotacaoDomainUpdate != null){
            return Anotacao.builder()
                    .id(anotacaoDomainEncontrado.getId())
                    .titulo(anotacaoDomainUpdate.getTitulo())
                    .descricao(anotacaoDomainUpdate.getDescricao())
                    .usuario(toUsuario(anotacaoDomainEncontrado.getUsuario()))
                    .createdAt(anotacaoDomainEncontrado.getCreatedAt())
                    .build();
        }
        return null;
    }
}
