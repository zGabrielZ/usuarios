package br.com.gabrielferreira.usuario.factory.domain;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoUpdateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import org.springframework.data.domain.Page;

import static br.com.gabrielferreira.usuario.factory.domain.UsuarioDomainFactory.*;
import static br.com.gabrielferreira.usuario.factory.domain.DominioDomainFactory.*;
import static br.com.gabrielferreira.usuario.utils.DataUtils.*;

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
            return AnotacaoDomain.builder()
                    .id(anotacao.getId())
                    .titulo(anotacao.getTitulo())
                    .descricao(anotacao.getDescricao())
                    .tipoAnotacao(toDominioDomain(anotacao.getTipoAnotacao()))
                    .dataLembrete(toFusoPadraoSistema(anotacao.getDataLembrete()))
                    .dataEstudoInicio(toFusoPadraoSistema(anotacao.getDataEstudoInicio()))
                    .dataEstudoFim(toFusoPadraoSistema(anotacao.getDataEstudoFim()))
                    .situacaoTipoAnotacao(toDominioDomain(anotacao.getSituacaoTipoAnotacao()))
                    .createdAt(toFusoPadraoSistema(anotacao.getCreatedAt()))
                    .updatedAt(toFusoPadraoSistema(anotacao.getUpdatedAt()))
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
