package br.com.gabrielferreira.usuario.factory.entity;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.entity.Anotacao;

import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuario.factory.entity.UsuarioFactory.*;
import static br.com.gabrielferreira.usuario.utils.DataUtils.*;
import static br.com.gabrielferreira.usuario.factory.entity.DominioFactory.*;

public class AnotacaoFactory {

    private AnotacaoFactory(){}

    public static Anotacao toCreateAnotacao(AnotacaoDomain anotacaoDomain){
        if(anotacaoDomain != null){
            ZonedDateTime dataLembrete = anotacaoDomain.getDataLembrete() != null ? anotacaoDomain.getDataLembrete().withZoneSameInstant(UTC) : null;
            ZonedDateTime dataEstudoInicio = anotacaoDomain.getDataEstudoInicio() != null ? anotacaoDomain.getDataEstudoInicio().withZoneSameInstant(UTC) : null;
            ZonedDateTime dataEstudoFim = anotacaoDomain.getDataEstudoFim() != null ? anotacaoDomain.getDataEstudoFim().withZoneSameInstant(UTC) : null;
            return Anotacao.builder()
                    .titulo(anotacaoDomain.getTitulo())
                    .descricao(anotacaoDomain.getDescricao())
                    .usuario(toUsuario(anotacaoDomain.getUsuario()))
                    .tipoAnotacao(toDominio(anotacaoDomain.getTipoAnotacao()))
                    .dataLembrete(dataLembrete)
                    .dataEstudoInicio(dataEstudoInicio)
                    .dataEstudoFim(dataEstudoFim)
                    .situacaoTipoAnotacao(toDominio(anotacaoDomain.getSituacaoTipoAnotacao()))
                    .build();
        }
        return null;
    }

    public static Anotacao toUpdateAnotacao(AnotacaoDomain anotacaoDomainEncontrado, AnotacaoDomain anotacaoDomainUpdate){
        if(anotacaoDomainEncontrado != null && anotacaoDomainUpdate != null){
            ZonedDateTime dataLembrete = anotacaoDomainUpdate.getDataLembrete() != null ? anotacaoDomainUpdate.getDataLembrete().withZoneSameInstant(UTC) : null;
            ZonedDateTime dataEstudoInicio = anotacaoDomainUpdate.getDataEstudoInicio() != null ? anotacaoDomainUpdate.getDataEstudoInicio().withZoneSameInstant(UTC) : null;
            ZonedDateTime dataEstudoFim = anotacaoDomainUpdate.getDataEstudoFim() != null ? anotacaoDomainUpdate.getDataEstudoFim().withZoneSameInstant(UTC) : null;
            return Anotacao.builder()
                    .id(anotacaoDomainEncontrado.getId())
                    .titulo(anotacaoDomainUpdate.getTitulo())
                    .descricao(anotacaoDomainUpdate.getDescricao())
                    .usuario(toUsuario(anotacaoDomainEncontrado.getUsuario()))
                    .tipoAnotacao(toDominio(anotacaoDomainUpdate.getTipoAnotacao()))
                    .dataLembrete(dataLembrete)
                    .dataEstudoInicio(dataEstudoInicio)
                    .dataEstudoFim(dataEstudoFim)
                    .situacaoTipoAnotacao(toDominio(anotacaoDomainUpdate.getSituacaoTipoAnotacao()))
                    .createdAt(anotacaoDomainEncontrado.getCreatedAt().withZoneSameInstant(UTC))
                    .build();
        }
        return null;
    }
}
