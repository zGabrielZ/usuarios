package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateAnotacaoInput;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class ValidCreateAnotacaoUseCase implements ValidCreateAnotacaoInput {

    @Override
    public void validarCampos(AnotacaoDomain anotacaoDomain) {
        anotacaoDomain.setTitulo(anotacaoDomain.getTitulo().trim());
        anotacaoDomain.setDescricao(anotacaoDomain.getDescricao().trim());
    }

    @Override
    public void validarDataInicioDataFimEstudo(AnotacaoDomain anotacaoDomain) {
        ZonedDateTime dataInicioEstudo = anotacaoDomain.getDataEstudoInicio();
        ZonedDateTime dataFimEstudo = anotacaoDomain.getDataEstudoFim();

        if(dataInicioEstudo.isAfter(dataFimEstudo) || dataInicioEstudo.equals(dataFimEstudo)){
            throw new RegraDeNegocioException("A data início do estudo não pode ser antes ou igual ao data fim do estudo");
        }

        long horas = ChronoUnit.HOURS.between(dataInicioEstudo, dataFimEstudo);
        if(horas > 5){
            throw new RegraDeNegocioException("O estudo não pode ultrapassar de 5 horas");
        }
    }

    @Override
    public void validarSituacaoFinalizada(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum) {
        if(situacao.getCodigo().equals(tipoAnotacaoEnum.name())){
            throw new RegraDeNegocioException("Não é possível finalizar a anotação pois já está finalizado");
        }
    }

    @Override
    public void validarSituacaoAberto(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum) {
        if(situacao.getCodigo().equals(tipoAnotacaoEnum.name())){
            throw new RegraDeNegocioException("Não é possível reabrir a anotação pois já está em aberto");
        }
    }

    @Override
    public void validarSituacaoEditar(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum) {
        if(situacao.getCodigo().equals(tipoAnotacaoEnum.name())){
            throw new RegraDeNegocioException("Não é possível editar a anotação pois já está finalizado");
        }
    }
}
