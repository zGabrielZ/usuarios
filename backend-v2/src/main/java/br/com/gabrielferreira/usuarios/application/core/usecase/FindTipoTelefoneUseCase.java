package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.SituacaoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindDominioOutput;

import java.util.List;

public class FindTipoTelefoneUseCase implements FindTipoTelefoneInput {

    private final FindDominioOutput findDominioOutput;

    public FindTipoTelefoneUseCase(FindDominioOutput findDominioOutput) {
        this.findDominioOutput = findDominioOutput;
    }

    @Override
    public DominioDomain findById(Long id) {
        return findDominioOutput.findByIdAndTipoCodigo(id, SituacaoAnotacaoEnum.TIPO_TELEFONE.name())
                .orElseThrow(() -> new NaoEncontradoException("Tipo de telefone informado não encontrado"));
    }

    @Override
    public List<DominioDomain> findAllByTipoCodigo() {
        return findDominioOutput.findAllByTipoCodigo(SituacaoAnotacaoEnum.TIPO_TELEFONE.name());
    }
}
