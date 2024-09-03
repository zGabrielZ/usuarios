package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindSituacaoAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateAnotacaoOutput;

public class UpdateAnotacaoUseCase implements UpdateAnotacaoInput {

    private static final String RASCUNHO_FINALIZADO = "RASCUNHO_FINALIZADO";

    private static final String LEMBRETE_FINALIZADO = "LEMBRETE_FINALIZADO";

    private final UpdateAnotacaoOutput updateAnotacaoOutput;

    private final FindAnotacaoInput findAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    public UpdateAnotacaoUseCase(UpdateAnotacaoOutput updateAnotacaoOutput,
                                 FindAnotacaoInput findAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput) {
        this.updateAnotacaoOutput = updateAnotacaoOutput;
        this.findAnotacaoInput = findAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
    }

    @Override
    public void finalizarAnotacaoRascunho(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals(RASCUNHO_FINALIZADO)){
            throw new RegraDeNegocioException("Não é possível finalizar a anotação pois já está finalizado");
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(RASCUNHO_FINALIZADO);
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void reabrirAnotacaoRascunho(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals("RASCUNHO_ABERTO")){
            throw new RegraDeNegocioException("Não é possível reabrir a anotação pois já está em aberto");
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo("RASCUNHO_ABERTO");
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacaoRascunho(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        if(anotacaoDomainEncontrado.getSituacaoTipoAnotacao().getCodigo().equals(RASCUNHO_FINALIZADO)){
            throw new RegraDeNegocioException("Não é possível editar a anotação pois já está finalizado");
        }

        anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        return updateAnotacaoOutput.updateAnotacao(anotacaoDomainEncontrado);
    }

    @Override
    public void finalizarAnotacaoEstudo(Long id, Long idUsuario) {

    }

    @Override
    public void reabrirAnotacaoEstudo(Long id, Long idUsuario) {

    }

    @Override
    public AnotacaoDomain updateAnotacaoEstudo(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        return null;
    }

    @Override
    public void finalizarAnotacaoLembrete(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals(LEMBRETE_FINALIZADO)){
            throw new RegraDeNegocioException("Não é possível finalizar a anotação pois já está finalizado");
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(LEMBRETE_FINALIZADO);
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void reabrirAnotacaoLembrete(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals("LEMBRETE_ABERTO")){
            throw new RegraDeNegocioException("Não é possível reabrir a anotação pois já está em aberto");
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo("LEMBRETE_ABERTO");
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacaoLembrete(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        if(anotacaoDomainEncontrado.getSituacaoTipoAnotacao().getCodigo().equals(LEMBRETE_FINALIZADO)){
            throw new RegraDeNegocioException("Não é possível editar a anotação pois já está finalizado");
        }

        anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        anotacaoDomainEncontrado.setDataLembrete(anotacaoDomainUpdate.getDataLembrete());
        return updateAnotacaoOutput.updateAnotacao(anotacaoDomainEncontrado);
    }
}
