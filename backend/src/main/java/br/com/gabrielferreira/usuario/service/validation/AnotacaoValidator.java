package br.com.gabrielferreira.usuario.service.validation;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.entity.enumeration.DominioEnumeration;
import br.com.gabrielferreira.usuario.entity.enumeration.TipoDominioEnumeration;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.service.DominioService;
import br.com.gabrielferreira.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static br.com.gabrielferreira.usuario.entity.enumeration.DominioEnumeration.*;
import static br.com.gabrielferreira.usuario.utils.DataUtils.*;

@Component
@RequiredArgsConstructor
public class AnotacaoValidator {

    private final UsuarioService usuarioService;

    private final DominioService dominioService;

    public void validarCampos(AnotacaoDomain anotacaoDomain){
        anotacaoDomain.setTitulo(anotacaoDomain.getTitulo().trim());
        anotacaoDomain.setDescricao(anotacaoDomain.getDescricao().trim());
    }

    public void validarTipoAnotacao(AnotacaoDomain anotacaoDomain){
        DominioDomain tipoAnotacao = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(anotacaoDomain.getTipoAnotacao().getId(), TipoDominioEnumeration.TIPO_ANOTACAO);
        anotacaoDomain.setTipoAnotacao(tipoAnotacao);

        if(isEstudo(tipoAnotacao.getCodigo())){
            validarDataInicioDataFimEstudo(anotacaoDomain.getDataEstudoInicio(), anotacaoDomain.getDataEstudoFim());

            anotacaoDomain.setDataLembrete(null);
            anotacaoDomain.setSituacaoTipoAnotacao(dominioService.buscarDominioPorCodigo(DominioEnumeration.ESTUDO_ANDAMENTO.name()));
        } else if(isLembrete(tipoAnotacao.getCodigo())){
            validarDataLembrete(anotacaoDomain.getDataLembrete());

            anotacaoDomain.setDataEstudoInicio(null);
            anotacaoDomain.setDataEstudoFim(null);
            anotacaoDomain.setSituacaoTipoAnotacao(dominioService.buscarDominioPorCodigo(DominioEnumeration.LEMBRETE_ABERTO.name()));
        } else if(isRascunho(tipoAnotacao.getCodigo())){
            anotacaoDomain.setDataLembrete(null);
            anotacaoDomain.setDataEstudoInicio(null);
            anotacaoDomain.setDataEstudoFim(null);
            anotacaoDomain.setSituacaoTipoAnotacao(dominioService.buscarDominioPorCodigo(DominioEnumeration.RASCUNHO_ABERTO.name()));
        }
    }

    public void validarUsuario(AnotacaoDomain anotacaoDomain){
        UsuarioDomain usuarioDomain = usuarioService.buscarUsuarioPorId(anotacaoDomain.getUsuario().getId());
        anotacaoDomain.setUsuario(usuarioDomain);
    }

    public void validarDataInicioDataFimEstudo(ZonedDateTime dataInicioEstudo, ZonedDateTime dataFimEstudo){
        validarData(dataInicioEstudo, "É necessário informar a data início estudo da anotação");
        validarData(dataFimEstudo, "É necessário informar a data fim estudo da anotação");
        validarDataFutura(dataInicioEstudo, "A data do estudo início da anotação deve ser no futuro");
        validarDataFutura(dataFimEstudo, "A data do estudo fim da anotação deve ser no futuro");

        if(dataInicioEstudo.isAfter(dataFimEstudo) || dataInicioEstudo.equals(dataFimEstudo)){
            throw new MsgException("A data início do estudo tem que ser antes da data fim");
        }

        long horas = ChronoUnit.HOURS.between(dataInicioEstudo, dataFimEstudo);
        if(horas > 5){
            throw new MsgException("O estudo não pode ultrapassar de 5 horas");
        }
    }

    public void validarDataLembrete(ZonedDateTime dataLembrete){
        validarData(dataLembrete, "É necessário informar a data lembrete da anotação");
        validarDataFutura(dataLembrete, "A data do lembrete da anotação deve ser no futuro");
    }

    private void validarDataFutura(ZonedDateTime data, String msg){
        ZonedDateTime dataAtual = ZonedDateTime.now(UTC);
        ZonedDateTime dataEntrada = data.withZoneSameInstant(UTC);

        if(dataEntrada.isBefore(dataAtual) || dataEntrada.equals(dataAtual)){
            throw new MsgException(msg);
        }
    }

    private void validarData(ZonedDateTime data, String msg){
        if(data == null){
            throw new MsgException(msg);
        }
    }
}
