package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindAnotacaoOutput;

import java.util.List;

public class FindAnotacaoUseCase implements FindAnotacaoInput {

    private static final String ANOTACAO_NAO_ENCONTRADA = "Anotação informado não encontrado";

    private final FindAnotacaoOutput findAnotacaoOutput;

    private final FindUsuarioInput findUsuarioInput;

    public FindAnotacaoUseCase(FindAnotacaoOutput findAnotacaoOutput,
                               FindUsuarioInput findUsuarioInput) {
        this.findAnotacaoOutput = findAnotacaoOutput;
        this.findUsuarioInput = findUsuarioInput;
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoRascunho(Long id, Long idUsuario) {
        return findAnotacaoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, TipoAnotacaoEnum.RASCUNHO.name(), idUsuario)
                .orElseThrow(() -> new NaoEncontradoException(ANOTACAO_NAO_ENCONTRADA));
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoEstudo(Long id, Long idUsuario) {
        return findAnotacaoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, TipoAnotacaoEnum.ESTUDO.name(), idUsuario)
                .orElseThrow(() -> new NaoEncontradoException(ANOTACAO_NAO_ENCONTRADA));
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoLembrete(Long id, Long idUsuario) {
        return findAnotacaoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, TipoAnotacaoEnum.LEMBRETE.name(), idUsuario)
                .orElseThrow(() -> new NaoEncontradoException(ANOTACAO_NAO_ENCONTRADA));
    }

    @Override
    public List<AnotacaoDomain> findAll(PageInfo pageInfo, String titulo, String descricao, Long idUsuario) {
        findUsuarioInput.findById(idUsuario);
        return findAnotacaoOutput.findAll(pageInfo, titulo, descricao, idUsuario);
    }
}
