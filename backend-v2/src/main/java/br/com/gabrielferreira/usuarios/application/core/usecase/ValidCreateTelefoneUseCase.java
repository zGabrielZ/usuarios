package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindDominioOutput;
import io.micrometer.common.util.StringUtils;

public class ValidCreateTelefoneUseCase implements ValidCreateTelefoneInput {

    private final FindDominioOutput findDominioOutput;

    public ValidCreateTelefoneUseCase(FindDominioOutput findDominioOutput) {
        this.findDominioOutput = findDominioOutput;
    }

    @Override
    public void validarCampos(TelefoneDomain telefoneDomain) {
        telefoneDomain.setNumero(telefoneDomain.getNumero().trim());
        telefoneDomain.setDdd(telefoneDomain.getDdd().trim());

        if(!StringUtils.isBlank(telefoneDomain.getDescricao())){
            telefoneDomain.setDescricao(telefoneDomain.getDescricao().trim());
        }
    }

    @Override
    public void validarTipoTelefoneExistente(DominioDomain tipoTelefone) {
        if(findDominioOutput.findByIdAndTipoCodigo(tipoTelefone.getId(), "TIPO_TELEFONE").isEmpty()){
            throw new NaoEncontradoException("Tipo de telefone informado não encontrado");
        }
    }

    @Override
    public void validarNumeroComTipoTelefone(TelefoneDomain telefoneDomain, DominioDomain tipoTelefone) {
        if(telefoneDomain.getNumero().length() == 8 && tipoTelefone.getCodigo().equals("CELULAR")){
            throw new RegraDeNegocioException(String.format("O número do telefone '%s' tem ser do tipo residencial", telefoneDomain.getTelefoneFormatado()));
        } else if(telefoneDomain.getNumero().length() == 9 && tipoTelefone.getCodigo().equals("RESIDENCIAL")){
            throw new RegraDeNegocioException(String.format("O número de telefone '%s' tem ser do tipo celular", telefoneDomain.getTelefoneFormatado()));
        }
    }
}
