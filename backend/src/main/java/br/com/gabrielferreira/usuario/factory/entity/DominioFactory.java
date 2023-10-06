package br.com.gabrielferreira.usuario.factory.entity;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.entity.Dominio;

import static br.com.gabrielferreira.usuario.factory.entity.TipoDominioFactory.*;

public class DominioFactory {

    private DominioFactory(){}

    public static Dominio toDominio(DominioDomain dominioDomain){
        if(dominioDomain != null){
            return Dominio.builder()
                    .id(dominioDomain.getId())
                    .descricao(dominioDomain.getDescricao())
                    .codigo(dominioDomain.getCodigo())
                    .tipo(toTipoDominio(dominioDomain.getTipo()))
                    .build();
        }
        return null;
    }
}
