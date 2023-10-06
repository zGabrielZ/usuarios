package br.com.gabrielferreira.usuario.factory.entity;

import br.com.gabrielferreira.usuario.domain.TipoDominioDomain;
import br.com.gabrielferreira.usuario.entity.TipoDominio;

public class TipoDominioFactory {

    private TipoDominioFactory(){}

    public static TipoDominio toTipoDominio(TipoDominioDomain tipoDominioDomain){
        if(tipoDominioDomain != null){
            return TipoDominio.builder()
                    .id(tipoDominioDomain.getId())
                    .descricao(tipoDominioDomain.getDescricao())
                    .codigo(tipoDominioDomain.getCodigo())
                    .build();
        }
        return null;
    }
}
