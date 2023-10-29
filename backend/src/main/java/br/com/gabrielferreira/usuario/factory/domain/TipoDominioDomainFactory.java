package br.com.gabrielferreira.usuario.factory.domain;

import br.com.gabrielferreira.usuario.domain.TipoDominioDomain;
import br.com.gabrielferreira.usuario.entity.TipoDominio;

import java.util.ArrayList;
import java.util.List;

public class TipoDominioDomainFactory {

    private TipoDominioDomainFactory(){}

    public static TipoDominioDomain toTipoDominioDomain(TipoDominio tipoDominio){
        if(tipoDominio != null){
            return TipoDominioDomain.builder()
                    .id(tipoDominio.getId())
                    .descricao(tipoDominio.getDescricao())
                    .codigo(tipoDominio.getCodigo())
                    .build();
        }
        return null;
    }

    public static TipoDominioDomain toTipoDominioDomain(Long id, String descricao, String codigo){
        return TipoDominioDomain.builder()
                .id(id)
                .descricao(descricao)
                .codigo(codigo)
                .build();
    }

    public static List<TipoDominioDomain> toTiposDominiosDomains(List<TipoDominio> tipoDominios){
        List<TipoDominioDomain> tipoDominioDomains = new ArrayList<>();
        tipoDominios.forEach(tipoDominio -> {
            TipoDominioDomain tipoDominioDomain = toTipoDominioDomain(tipoDominio);
            tipoDominioDomains.add(tipoDominioDomain);
        });
        return tipoDominioDomains;
    }
}
