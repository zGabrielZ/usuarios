package br.com.gabrielferreira.usuario.factory.domain;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.dto.request.GeneroCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.TipoTelefoneCreateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Dominio;
import br.com.gabrielferreira.usuario.repository.projection.DominioProjection;

import java.util.ArrayList;
import java.util.List;

import static br.com.gabrielferreira.usuario.factory.domain.TipoDominioDomainFactory.*;

public class DominioDomainFactory {

    private DominioDomainFactory(){}

    public static DominioDomain toCreateGeneroDomain(GeneroCreateRequestDTO generoCreateRequestDTO){
        if(generoCreateRequestDTO != null){
            return DominioDomain.builder()
                    .id(generoCreateRequestDTO.getId())
                    .build();
        }
        return null;
    }

    public static DominioDomain toCreateTipoTelefoneDomain(TipoTelefoneCreateRequestDTO telefoneCreateRequestDTO){
        if(telefoneCreateRequestDTO != null){
            return DominioDomain.builder()
                    .id(telefoneCreateRequestDTO.getId())
                    .build();
        }
        return null;
    }

    public static DominioDomain toDominioDomain(Dominio dominio){
        if(dominio != null){
            return DominioDomain.builder()
                    .id(dominio.getId())
                    .descricao(dominio.getDescricao())
                    .codigo(dominio.getCodigo())
                    .tipo(toTipoDominioDomain(dominio.getTipo()))
                    .build();
        }
        return null;
    }

    public static DominioDomain toDominioDomain(DominioProjection dominioProjection){
        if(dominioProjection != null){
            return DominioDomain.builder()
                    .id(dominioProjection.getId())
                    .descricao(dominioProjection.getDescricao())
                    .codigo(dominioProjection.getCodigo())
                    .tipo(toTipoDominioDomain(dominioProjection.getIdTipoDominio(), dominioProjection.getDescricaoTipoDominio(), dominioProjection.getCodigoTipoDominio()))
                    .build();
        }
        return null;
    }

    public static List<DominioDomain> toDominiosDomains(List<DominioProjection> dominioProjections){
        List<DominioDomain> dominioDomains = new ArrayList<>();
        dominioProjections.forEach(dominioProjection -> {
            DominioDomain dominioDomain = toDominioDomain(dominioProjection);
            dominioDomains.add(dominioDomain);
        });
        return dominioDomains;
    }
}
