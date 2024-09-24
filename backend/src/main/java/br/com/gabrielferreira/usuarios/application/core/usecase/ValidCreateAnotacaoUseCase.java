package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
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
}
