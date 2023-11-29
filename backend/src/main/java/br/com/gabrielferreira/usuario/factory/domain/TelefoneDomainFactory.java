package br.com.gabrielferreira.usuario.factory.domain;

import br.com.gabrielferreira.usuario.domain.TelefoneDomain;
import br.com.gabrielferreira.usuario.dto.request.TelefoneCreateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Telefone;
import static br.com.gabrielferreira.usuario.factory.domain.DominioDomainFactory.*;
import static br.com.gabrielferreira.usuario.utils.DataUtils.*;

public class TelefoneDomainFactory {

    private TelefoneDomainFactory(){}

    public static TelefoneDomain toCreateTelefoneDomain(TelefoneCreateRequestDTO telefoneCreateRequestDTO){
        if(telefoneCreateRequestDTO != null){
            return TelefoneDomain.builder()
                    .numero(telefoneCreateRequestDTO.getNumero())
                    .ddd(telefoneCreateRequestDTO.getDdd())
                    .descricao(telefoneCreateRequestDTO.getDescricao())
                    .tipoTelefone(toCreateTipoTelefoneDomain(telefoneCreateRequestDTO.getTipoTelefone()))
                    .build();
        }
        return null;
    }

    public static TelefoneDomain toTelefoneDomain(Telefone telefone){
        if(telefone != null){
            return TelefoneDomain.builder()
                    .id(telefone.getId())
                    .numero(telefone.getNumero())
                    .ddd(telefone.getDdd())
                    .descricao(telefone.getDescricao())
                    .tipoTelefone(toDominioDomain(telefone.getTipoTelefone()))
                    .createdAt(toFusoPadraoSistema(telefone.getCreatedAt()))
                    .updatedAt(toFusoPadraoSistema(telefone.getUpdatedAt()))
                    .build();
        }
        return null;
    }
}
