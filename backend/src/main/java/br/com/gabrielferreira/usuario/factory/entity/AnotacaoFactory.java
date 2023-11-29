package br.com.gabrielferreira.usuario.factory.entity;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.entity.Anotacao;

import static br.com.gabrielferreira.usuario.factory.entity.UsuarioFactory.*;
import static br.com.gabrielferreira.usuario.utils.DataUtils.*;
import static br.com.gabrielferreira.usuario.factory.entity.DominioFactory.*;

public class AnotacaoFactory {

    private AnotacaoFactory(){}

    public static Anotacao toCreateAnotacao(AnotacaoDomain anotacaoDomain){
        if(anotacaoDomain != null){
            return Anotacao.builder()
                    .titulo(anotacaoDomain.getTitulo())
                    .descricao(anotacaoDomain.getDescricao())
                    .usuario(toUsuario(anotacaoDomain.getUsuario()))
                    .tipoAnotacao(toDominio(anotacaoDomain.getTipoAnotacao()))
                    .dataLembrete(toUtc(anotacaoDomain.getDataLembrete()))
                    .dataEstudoInicio(toUtc(anotacaoDomain.getDataEstudoInicio()))
                    .dataEstudoFim(toUtc(anotacaoDomain.getDataEstudoFim()))
                    .situacaoTipoAnotacao(toDominio(anotacaoDomain.getSituacaoTipoAnotacao()))
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
                    .tipoAnotacao(toDominio(anotacaoDomainUpdate.getTipoAnotacao()))
                    .dataLembrete(toUtc(anotacaoDomainUpdate.getDataLembrete()))
                    .dataEstudoInicio(toUtc(anotacaoDomainUpdate.getDataEstudoInicio()))
                    .dataEstudoFim(toUtc(anotacaoDomainUpdate.getDataEstudoFim()))
                    .situacaoTipoAnotacao(toDominio(anotacaoDomainUpdate.getSituacaoTipoAnotacao()))
                    .createdAt(toUtc(anotacaoDomainEncontrado.getCreatedAt()))
                    .build();
        }
        return null;
    }
}
