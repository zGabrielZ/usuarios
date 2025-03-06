package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateAnotacaoOutput;
import br.com.gabrielferreira.usuarios.application.validator.AnotacaoValidator;

public class CreateAnotacaoUseCase implements CreateAnotacaoInput {

    private final CreateAnotacaoOutput createAnotacaoOutput;

    private final AnotacaoValidator anotacaoValidator;

    private final FindTipoAnotacaoInput findTipoAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final FindUsuarioInput findUsuarioInput;

    private final UserCurrentInput userCurrentInput;

    public CreateAnotacaoUseCase(CreateAnotacaoOutput createAnotacaoOutput,
                                 AnotacaoValidator anotacaoValidator,
                                 FindTipoAnotacaoInput findTipoAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                 FindUsuarioInput findUsuarioInput,
                                 UserCurrentInput userCurrentInput) {
        this.createAnotacaoOutput = createAnotacaoOutput;
        this.anotacaoValidator = anotacaoValidator;
        this.findTipoAnotacaoInput = findTipoAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.findUsuarioInput = findUsuarioInput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public AnotacaoDomain createRascunho(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        userCurrentInput.validarAdminOuProprioUsuario(idUsuario);
        anotacaoValidator.validarCampos(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_ABERTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        createAnotacao(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain createEstudo(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        userCurrentInput.validarAdminOuProprioUsuario(idUsuario);
        anotacaoValidator.validarCampos(anotacaoDomain);
        anotacaoValidator.validarDataInicioDataFimEstudo(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_ANDAMENTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        createAnotacao(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain createLembrete(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        userCurrentInput.validarAdminOuProprioUsuario(idUsuario);
        anotacaoValidator.validarCampos(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE_ABERTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        createAnotacao(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }

    private void createAnotacao(AnotacaoDomain anotacaoDomain, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain) {
        anotacaoDomain.setTipoAnotacao(tipoAnotacaoDomain);
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoAnotacaoDomain);
        anotacaoDomain.setUsuario(usuarioDomain);
    }
}
