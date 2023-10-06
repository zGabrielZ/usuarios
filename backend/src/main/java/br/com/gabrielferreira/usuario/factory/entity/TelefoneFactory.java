package br.com.gabrielferreira.usuario.factory.entity;

import br.com.gabrielferreira.usuario.domain.TelefoneDomain;
import br.com.gabrielferreira.usuario.entity.Telefone;

import static br.com.gabrielferreira.usuario.factory.entity.DominioFactory.*;

public class TelefoneFactory {

    private TelefoneFactory(){}

    public static Telefone toCreateTelefone(TelefoneDomain telefoneDomain){
        if(telefoneDomain != null){
            return Telefone.builder()
                    .numero(telefoneDomain.getNumero())
                    .ddd(telefoneDomain.getDdd())
                    .descricao(telefoneDomain.getDescricao())
                    .tipoTelefone(toDominio(telefoneDomain.getTipoTelefone()))
                    .build();
        }
        return null;
    }

    public static Telefone toUpdateTelefone(TelefoneDomain telefoneDomainEncontrado, TelefoneDomain telefoneDomainUpdate){
        if(telefoneDomainEncontrado != null && telefoneDomainUpdate != null){
            return Telefone.builder()
                    .id(telefoneDomainEncontrado.getId())
                    .numero(telefoneDomainUpdate.getNumero())
                    .ddd(telefoneDomainUpdate.getDdd())
                    .descricao(telefoneDomainUpdate.getDescricao())
                    .tipoTelefone(toDominio(telefoneDomainUpdate.getTipoTelefone()))
                    .createdAt(telefoneDomainEncontrado.getCreatedAt())
                    .build();
        }
        return null;
    }
}
