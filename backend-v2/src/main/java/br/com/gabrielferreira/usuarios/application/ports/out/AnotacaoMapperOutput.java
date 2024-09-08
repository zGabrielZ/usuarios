package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface AnotacaoMapperOutput {

    AnotacaoDomain createRascunho(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain);

    AnotacaoDomain createEstudo(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain);

    AnotacaoDomain createLembrete(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain);

    AnotacaoDomain updateFinalizarReabrir(AnotacaoDomain anotacaoDomainFound, DominioDomain situacaoAnotacaoDomain);

    AnotacaoDomain updateAnotacao(AnotacaoDomain anotacaoDomainFound, AnotacaoDomain anotacaoDomainUpdate);
}
