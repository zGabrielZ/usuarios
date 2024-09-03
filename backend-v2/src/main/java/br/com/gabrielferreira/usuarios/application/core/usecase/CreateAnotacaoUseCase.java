package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateAnotacaoOutput;

import static br.com.gabrielferreira.usuarios.utils.DataUtils.*;

public class CreateAnotacaoUseCase implements CreateAnotacaoInput {

    private final CreateAnotacaoOutput createAnotacaoOutput;

    private final ValidCreateAnotacaoInput validCreateAnotacaoInput;

    private final FindTipoAnotacaoInput findTipoAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final FindUsuarioInput findUsuarioInput;

    public CreateAnotacaoUseCase(CreateAnotacaoOutput createAnotacaoOutput,
                                 ValidCreateAnotacaoInput validCreateAnotacaoInput,
                                 FindTipoAnotacaoInput findTipoAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                 FindUsuarioInput findUsuarioInput) {
        this.createAnotacaoOutput = createAnotacaoOutput;
        this.validCreateAnotacaoInput = validCreateAnotacaoInput;
        this.findTipoAnotacaoInput = findTipoAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.findUsuarioInput = findUsuarioInput;
    }

    @Override
    public AnotacaoDomain createRascunho(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo("RASCUNHO");
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo("RASCUNHO_ABERTO");
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        anotacaoDomain.setTipoAnotacao(tipoAnotacaoDomain);
        anotacaoDomain.setUsuario(usuarioDomain);
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoAnotacaoDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain createEstudo(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        return null;
    }

    @Override
    public AnotacaoDomain createLembrete(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo("LEMBRETE");
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo("RASCUNHO_ABERTO");
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        anotacaoDomain.setTipoAnotacao(tipoAnotacaoDomain);
        anotacaoDomain.setUsuario(usuarioDomain);
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoAnotacaoDomain);
        anotacaoDomain.setDataLembrete(toUtc(anotacaoDomain.getDataLembrete()));

        return createAnotacaoOutput.create(anotacaoDomain);
    }
}
