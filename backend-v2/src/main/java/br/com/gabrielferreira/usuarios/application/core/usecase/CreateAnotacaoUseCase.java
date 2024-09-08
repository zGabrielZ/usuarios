package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.AnotacaoMapperOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateAnotacaoOutput;

public class CreateAnotacaoUseCase implements CreateAnotacaoInput {

    private final CreateAnotacaoOutput createAnotacaoOutput;

    private final ValidCreateAnotacaoInput validCreateAnotacaoInput;

    private final FindTipoAnotacaoInput findTipoAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final FindUsuarioInput findUsuarioInput;

    private final AnotacaoMapperOutput anotacaoMapperOutput;

    public CreateAnotacaoUseCase(CreateAnotacaoOutput createAnotacaoOutput,
                                 ValidCreateAnotacaoInput validCreateAnotacaoInput,
                                 FindTipoAnotacaoInput findTipoAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                 FindUsuarioInput findUsuarioInput,
                                 AnotacaoMapperOutput anotacaoMapperOutput) {
        this.createAnotacaoOutput = createAnotacaoOutput;
        this.validCreateAnotacaoInput = validCreateAnotacaoInput;
        this.findTipoAnotacaoInput = findTipoAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.findUsuarioInput = findUsuarioInput;
        this.anotacaoMapperOutput = anotacaoMapperOutput;
    }

    @Override
    public AnotacaoDomain createRascunho(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_ABERTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        AnotacaoDomain anotacaoDomainCreate = anotacaoMapperOutput.createRascunho(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);
        return createAnotacaoOutput.create(anotacaoDomainCreate);
    }

    @Override
    public AnotacaoDomain createEstudo(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);
        validCreateAnotacaoInput.validarDataInicioDataFimEstudo(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_ANDAMENTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        AnotacaoDomain anotacaoDomainCreate = anotacaoMapperOutput.createEstudo(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);
        return createAnotacaoOutput.create(anotacaoDomainCreate);
    }

    @Override
    public AnotacaoDomain createLembrete(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE_ABERTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        AnotacaoDomain anotacaoDomainCreate = anotacaoMapperOutput.createLembrete(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);
        return createAnotacaoOutput.create(anotacaoDomainCreate);
    }
}
