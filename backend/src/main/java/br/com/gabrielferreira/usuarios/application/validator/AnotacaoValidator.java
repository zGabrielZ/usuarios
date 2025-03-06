package br.com.gabrielferreira.usuarios.application.validator;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;

public interface AnotacaoValidator {

    void validarCampos(AnotacaoDomain anotacaoDomain);

    void validarDataInicioDataFimEstudo(AnotacaoDomain anotacaoDomain);

    void validarSituacaoFinalizada(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum);

    void validarSituacaoAberto(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum);

    void validarSituacaoEditar(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum);
}
