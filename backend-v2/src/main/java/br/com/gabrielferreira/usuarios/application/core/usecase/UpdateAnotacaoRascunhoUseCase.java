package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoRascunhoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindSituacaoAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateAnotacaoRascunhoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateAnotacaoRascunhoOutput;

public class UpdateAnotacaoRascunhoUseCase implements UpdateAnotacaoRascunhoInput {

    private final UpdateAnotacaoRascunhoOutput updateAnotacaoRascunhoOutput;

    private final FindAnotacaoRascunhoInput findAnotacaoRascunhoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    public UpdateAnotacaoRascunhoUseCase(UpdateAnotacaoRascunhoOutput updateAnotacaoRascunhoOutput,
                                         FindAnotacaoRascunhoInput findAnotacaoRascunhoInput,
                                         FindSituacaoAnotacaoInput findSituacaoAnotacaoInput) {
        this.updateAnotacaoRascunhoOutput = updateAnotacaoRascunhoOutput;
        this.findAnotacaoRascunhoInput = findAnotacaoRascunhoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
    }

    @Override
    public void finalizarAnotacao(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoRascunhoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals("RASCUNHO_FINALIZADO")){
            throw new RegraDeNegocioException("Não é possível finalizar a anotação pois já está finalizado");
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo("RASCUNHO_FINALIZADO");
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoRascunhoOutput.finalizarAnotacao(anotacaoDomain);
    }
}
