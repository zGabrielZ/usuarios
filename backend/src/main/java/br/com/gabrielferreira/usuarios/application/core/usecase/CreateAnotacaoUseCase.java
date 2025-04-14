package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindSituacaoAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateAnotacaoOutput;

public class CreateAnotacaoUseCase implements CreateAnotacaoInput {

    private final CreateAnotacaoOutput createAnotacaoOutput;

    private final FindTipoAnotacaoInput findTipoAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final FindUsuarioInput findUsuarioInput;

    public CreateAnotacaoUseCase(CreateAnotacaoOutput createAnotacaoOutput,
                                 FindTipoAnotacaoInput findTipoAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                 FindUsuarioInput findUsuarioInput) {
        this.createAnotacaoOutput = createAnotacaoOutput;
        this.findTipoAnotacaoInput = findTipoAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.findUsuarioInput = findUsuarioInput;
    }

    @Override
    public AnotacaoDomain createRascunho(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        anotacaoDomain.validarCampos();

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_ABERTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        createAnotacao(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain createEstudo(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        anotacaoDomain.validarCampos();
        anotacaoDomain.validarDataInicioDataFimEstudo();

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO.name());
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_ANDAMENTO.name());
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        createAnotacao(anotacaoDomain, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain createLembrete(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        anotacaoDomain.validarCampos();

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
