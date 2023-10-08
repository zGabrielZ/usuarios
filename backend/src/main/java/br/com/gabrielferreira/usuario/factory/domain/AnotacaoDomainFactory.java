package br.com.gabrielferreira.usuario.factory.domain;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoUpdateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import org.springframework.data.domain.Page;
import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuario.utils.DataUtils.FUSO_HORARIO_PADRAO_SISTEMA;
import static br.com.gabrielferreira.usuario.factory.domain.UsuarioDomainFactory.*;
import static br.com.gabrielferreira.usuario.factory.domain.DominioDomainFactory.*;

public class AnotacaoDomainFactory {

    private AnotacaoDomainFactory(){}

    public static AnotacaoDomain toCreateAnotacao(AnotacaoCreateRequestDTO anotacaoCreateRequestDTO){
        if(anotacaoCreateRequestDTO != null){
            UsuarioDomain usuarioDomain = UsuarioDomain.builder().id(anotacaoCreateRequestDTO.getUsuario().getId()).build();
            DominioDomain tipoAnotacaoDomain = DominioDomain.builder().id(anotacaoCreateRequestDTO.getTipoAnotacao().getId()).build();
            return AnotacaoDomain.builder()
                    .titulo(anotacaoCreateRequestDTO.getTitulo())
                    .descricao(anotacaoCreateRequestDTO.getDescricao())
                    .usuario(usuarioDomain)
                    .tipoAnotacao(tipoAnotacaoDomain)
                    .dataLembrete(anotacaoCreateRequestDTO.getDataLembrete())
                    .dataEstudoInicio(anotacaoCreateRequestDTO.getDataEstudoInicio())
                    .dataEstudoFim(anotacaoCreateRequestDTO.getDataEstudoFim())
                    .build();
        }
        return null;
    }

    public static AnotacaoDomain toUpdateAnotacao(Long id, AnotacaoUpdateRequestDTO anotacaoUpdateRequestDTO){
        if(id != null && anotacaoUpdateRequestDTO != null){
            DominioDomain tipoAnotacaoDomain = DominioDomain.builder().id(anotacaoUpdateRequestDTO.getTipoAnotacao().getId()).build();
            return AnotacaoDomain.builder()
                    .id(id)
                    .titulo(anotacaoUpdateRequestDTO.getTitulo())
                    .descricao(anotacaoUpdateRequestDTO.getDescricao())
                    .tipoAnotacao(tipoAnotacaoDomain)
                    .dataLembrete(anotacaoUpdateRequestDTO.getDataLembrete())
                    .dataEstudoInicio(anotacaoUpdateRequestDTO.getDataEstudoInicio())
                    .dataEstudoFim(anotacaoUpdateRequestDTO.getDataEstudoFim())
                    .build();
        }
        return null;
    }

    public static AnotacaoDomain toAnotacao(Anotacao anotacao){
        if(anotacao != null){
            ZonedDateTime dataLembrete = anotacao.getDataLembrete() != null ? anotacao.getDataLembrete().withZoneSameInstant(FUSO_HORARIO_PADRAO_SISTEMA) : null;
            ZonedDateTime dataEstudoInicio = anotacao.getDataEstudoInicio() != null ? anotacao.getDataEstudoInicio().withZoneSameInstant(FUSO_HORARIO_PADRAO_SISTEMA) : null;
            ZonedDateTime dataEstudoFim = anotacao.getDataEstudoFim() != null ? anotacao.getDataEstudoFim().withZoneSameInstant(FUSO_HORARIO_PADRAO_SISTEMA) : null;

            ZonedDateTime createdAt = anotacao.getCreatedAt() != null ? anotacao.getCreatedAt().withZoneSameInstant(FUSO_HORARIO_PADRAO_SISTEMA) : null;
            ZonedDateTime updateAt = anotacao.getUpdatedAt() != null ? anotacao.getUpdatedAt().withZoneSameInstant(FUSO_HORARIO_PADRAO_SISTEMA) : null;
            return AnotacaoDomain.builder()
                    .id(anotacao.getId())
                    .titulo(anotacao.getTitulo())
                    .descricao(anotacao.getDescricao())
                    .tipoAnotacao(toDominioDomain(anotacao.getTipoAnotacao()))
                    .dataLembrete(dataLembrete)
                    .dataEstudoInicio(dataEstudoInicio)
                    .dataEstudoFim(dataEstudoFim)
                    .situacaoTipoAnotacao(toDominioDomain(anotacao.getSituacaoTipoAnotacao()))
                    .createdAt(createdAt)
                    .updatedAt(updateAt)
                    .build();
        }
        return null;
    }

    public static AnotacaoDomain toAnotacaoComUsuario(Anotacao anotacao){
        AnotacaoDomain anotacaoDomain = toAnotacao(anotacao);
        if(anotacao != null){
            anotacaoDomain.setUsuario(toUsuario(anotacao.getUsuario()));
        }
        return anotacaoDomain;
    }

    public static Page<AnotacaoDomain> toAnotacoesDomains(Page<Anotacao> anotacoes){
        return anotacoes.map(AnotacaoDomainFactory::toAnotacao);
    }
}
