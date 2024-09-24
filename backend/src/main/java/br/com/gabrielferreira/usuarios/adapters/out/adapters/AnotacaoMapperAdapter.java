package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper.AnotacaoEntityMapper;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.AnotacaoMapperOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnotacaoMapperAdapter implements AnotacaoMapperOutput {

    private final AnotacaoEntityMapper anotacaoEntityMapper;

    @Override
    public AnotacaoDomain createRascunho(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain) {
        return anotacaoEntityMapper.createAnotacaoDomainRascunho(anotacaoDomainCreate, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);
    }

    @Override
    public AnotacaoDomain createEstudo(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain) {
        return anotacaoEntityMapper.createAnotacaoDomainEstudo(anotacaoDomainCreate, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);
    }

    @Override
    public AnotacaoDomain createLembrete(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain) {
        return anotacaoEntityMapper.createAnotacaoDomainLembrete(anotacaoDomainCreate, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);
    }

    @Override
    public AnotacaoDomain updateFinalizarReabrir(AnotacaoDomain anotacaoDomainFound, DominioDomain situacaoAnotacaoDomain) {
        return anotacaoEntityMapper.updateFinalizarReabrirAnotacaoDomain(anotacaoDomainFound, situacaoAnotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacao(AnotacaoDomain anotacaoDomainFound, AnotacaoDomain anotacaoDomainUpdate) {
        return anotacaoEntityMapper.updateAnotacao(anotacaoDomainFound, anotacaoDomainUpdate);
    }
}
