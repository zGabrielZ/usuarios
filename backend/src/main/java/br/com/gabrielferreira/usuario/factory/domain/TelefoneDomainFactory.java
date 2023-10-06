package br.com.gabrielferreira.usuario.factory.domain;

import br.com.gabrielferreira.usuario.domain.TelefoneDomain;
import br.com.gabrielferreira.usuario.dto.request.TelefoneCreateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Telefone;

import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuario.factory.domain.DominioDomainFactory.*;
import static br.com.gabrielferreira.usuario.utils.DataUtils.FUSO_HORARIO_PADRAO_SISTEMA;

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
            ZonedDateTime createdAt = telefone.getCreatedAt() != null ? telefone.getCreatedAt().withZoneSameInstant(FUSO_HORARIO_PADRAO_SISTEMA) : null;
            ZonedDateTime updateAt = telefone.getUpdatedAt() != null ? telefone.getUpdatedAt().withZoneSameInstant(FUSO_HORARIO_PADRAO_SISTEMA) : null;
            return TelefoneDomain.builder()
                    .id(telefone.getId())
                    .numero(telefone.getNumero())
                    .ddd(telefone.getDdd())
                    .descricao(telefone.getDescricao())
                    .tipoTelefone(toDominioDomain(telefone.getTipoTelefone()))
                    .createdAt(createdAt)
                    .updatedAt(updateAt)
                    .build();
        }
        return null;
    }
}
