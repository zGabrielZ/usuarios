package br.com.gabrielferreira.usuario.service.validation;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.domain.TelefoneDomain;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.service.DominioService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.gabrielferreira.usuario.entity.enumeration.DominioEnumeration.*;
import static br.com.gabrielferreira.usuario.entity.enumeration.TipoDominioEnumeration.*;

@Component
@RequiredArgsConstructor
public class TelefoneValidator {

    private final DominioService dominioService;

    public void validarCampos(TelefoneDomain telefoneDomain){
        telefoneDomain.setNumero(telefoneDomain.getNumero().trim());
        telefoneDomain.setDdd(telefoneDomain.getDdd().trim());

        if(!StringUtils.isBlank(telefoneDomain.getDescricao())){
            telefoneDomain.setDescricao(telefoneDomain.getDescricao().trim());
        }
    }

    public void validarTipoTelefone(TelefoneDomain telefoneDomain){
        DominioDomain tipoTelefone = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(telefoneDomain.getTipoTelefone().getId(), TIPO_TELEFONE);
        telefoneDomain.setTipoTelefone(tipoTelefone);
    }

    public void validarNumeroComTipoTelefone(TelefoneDomain telefoneDomain, DominioDomain dominioDomain){
        if(telefoneDomain.getNumero().length() == 8 && isCelular(dominioDomain.getCodigo())){
            throw new MsgException(String.format("O número do telefone '%s' tem ser do tipo residencial", telefoneDomain.getTelefoneFormatado()));
        } else if(telefoneDomain.getNumero().length() == 9 && isResidencial(dominioDomain.getCodigo())){
            throw new MsgException(String.format("O número de telefone '%s' tem ser do tipo celular", telefoneDomain.getTelefoneFormatado()));
        }
    }
}
