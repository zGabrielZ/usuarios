package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.exception.ForbiddenException;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateAnotacaoOutput;

public class CreateAnotacaoUseCase implements CreateAnotacaoInput {

    private final CreateAnotacaoOutput createAnotacaoOutput;

    private final ValidCreateAnotacaoInput validCreateAnotacaoInput;

    private final FindTipoAnotacaoInput findTipoAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final FindUsuarioInput findUsuarioInput;

    private final UserCurrentInput userCurrentInput;

    public CreateAnotacaoUseCase(CreateAnotacaoOutput createAnotacaoOutput,
                                 ValidCreateAnotacaoInput validCreateAnotacaoInput,
                                 FindTipoAnotacaoInput findTipoAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                 FindUsuarioInput findUsuarioInput,
                                 UserCurrentInput userCurrentInput) {
        this.createAnotacaoOutput = createAnotacaoOutput;
        this.validCreateAnotacaoInput = validCreateAnotacaoInput;
        this.findTipoAnotacaoInput = findTipoAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.findUsuarioInput = findUsuarioInput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public AnotacaoDomain createRascunho(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_ABERTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        createAnotacao(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain createEstudo(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);
        validCreateAnotacaoInput.validarDataInicioDataFimEstudo(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_ANDAMENTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        createAnotacao(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain createLembrete(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);

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

    private void validarAdminOuProprioUsuario(Long idUsuario){
        UsuarioDomain usuario = userCurrentInput.getUserCurrent();
        if(!usuario.getId().equals(idUsuario) && usuario.isNaoContemPerfil(RoleEnum.ROLE_ADMIN)){
            throw new ForbiddenException("Você não tem a permissão de realizar esta criação");
        }
    }
}
