package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateAnotacaoOutput;

public class CreateAnotacaoRascunhoUseCase implements CreateAnotacaoRascunhoInput {

    private final CreateAnotacaoOutput createAnotacaoOutput;

    private final FindTipoAnotacaoInput findTipoAnotacaoInput;

    private final FindUsuarioInput findUsuarioInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final ValidCreateAnotacaoInput validCreateAnotacaoInput;

    public CreateAnotacaoRascunhoUseCase(CreateAnotacaoOutput createAnotacaoOutput,
                                         FindTipoAnotacaoInput findTipoAnotacaoInput,
                                         FindUsuarioInput findUsuarioInput,
                                         FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                         ValidCreateAnotacaoInput validCreateAnotacaoInput) {
        this.createAnotacaoOutput = createAnotacaoOutput;
        this.findTipoAnotacaoInput = findTipoAnotacaoInput;
        this.findUsuarioInput = findUsuarioInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.validCreateAnotacaoInput = validCreateAnotacaoInput;
    }

    @Override
    public AnotacaoDomain create(AnotacaoDomain anotacaoDomain, Long idUsuario) {
        validCreateAnotacaoInput.validarCampos(anotacaoDomain);

        DominioDomain tipoAnotacaoDomain = findTipoAnotacaoInput.findByCodigo("RASCUNHO");
        DominioDomain situacaoAnotacaoDomain = findSituacaoAnotacaoInput.findByCodigo("RASCUNHO_ABERTO");
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(idUsuario);

        anotacaoDomain.setTipoAnotacao(tipoAnotacaoDomain);
        anotacaoDomain.setUsuario(usuarioDomain);
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoAnotacaoDomain);

        return createAnotacaoOutput.create(anotacaoDomain);
    }
}
