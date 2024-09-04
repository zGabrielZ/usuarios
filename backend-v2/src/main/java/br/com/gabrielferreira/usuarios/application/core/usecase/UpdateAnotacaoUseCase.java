package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindSituacaoAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateAnotacaoOutput;

import static br.com.gabrielferreira.usuarios.utils.DataUtils.*;

public class UpdateAnotacaoUseCase implements UpdateAnotacaoInput {

    private static final String MSG_FINALIZAR = "Não é possível finalizar a anotação pois já está finalizado";

    private static final String MSG_REABRIR = "Não é possível reabrir a anotação pois já está em aberto";

    private static final String MSG_EDITAR = "Não é possível editar a anotação pois já está finalizado";

    private final UpdateAnotacaoOutput updateAnotacaoOutput;

    private final FindAnotacaoInput findAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final ValidCreateAnotacaoInput validCreateAnotacaoInput;

    public UpdateAnotacaoUseCase(UpdateAnotacaoOutput updateAnotacaoOutput,
                                 FindAnotacaoInput findAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                 ValidCreateAnotacaoInput validCreateAnotacaoInput) {
        this.updateAnotacaoOutput = updateAnotacaoOutput;
        this.findAnotacaoInput = findAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.validCreateAnotacaoInput = validCreateAnotacaoInput;
    }

    @Override
    public void finalizarAnotacaoRascunho(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.RASCUNHO_FINALIZADO.name())){
            throw new RegraDeNegocioException(MSG_FINALIZAR);
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_FINALIZADO.name());
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void reabrirAnotacaoRascunho(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.RASCUNHO_ABERTO.name())){
            throw new RegraDeNegocioException(MSG_REABRIR);
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_ABERTO.name());
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacaoRascunho(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);

        if(anotacaoDomainEncontrado.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.RASCUNHO_FINALIZADO.name())){
            throw new RegraDeNegocioException(MSG_EDITAR);
        }

        anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        return updateAnotacaoOutput.updateAnotacao(anotacaoDomainEncontrado);
    }

    @Override
    public void finalizarAnotacaoEstudo(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.ESTUDO_FINALIZADO.name())){
            throw new RegraDeNegocioException(MSG_FINALIZAR);
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_FINALIZADO.name());
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void reabrirAnotacaoEstudo(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.ESTUDO_ANDAMENTO.name())){
            throw new RegraDeNegocioException(MSG_REABRIR);
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_ANDAMENTO.name());
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacaoEstudo(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarDataInicioDataFimEstudo(anotacaoDomainUpdate);

        if(anotacaoDomainEncontrado.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.ESTUDO_FINALIZADO.name())){
            throw new RegraDeNegocioException(MSG_EDITAR);
        }

        anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        anotacaoDomainEncontrado.setDataEstudoInicio(toUtc(anotacaoDomainUpdate.getDataEstudoInicio()));
        anotacaoDomainEncontrado.setDataEstudoFim(toUtc(anotacaoDomainUpdate.getDataEstudoFim()));
        return updateAnotacaoOutput.updateAnotacao(anotacaoDomainEncontrado);
    }

    @Override
    public void finalizarAnotacaoLembrete(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.LEMBRETE_FINALIZADO.name())){
            throw new RegraDeNegocioException(MSG_FINALIZAR);
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE_FINALIZADO.name());
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void reabrirAnotacaoLembrete(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        if(anotacaoDomain.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.LEMBRETE_ABERTO.name())){
            throw new RegraDeNegocioException(MSG_REABRIR);
        }

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE_ABERTO.name());
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacaoLembrete(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);

        if(anotacaoDomainEncontrado.getSituacaoTipoAnotacao().getCodigo().equals(TipoAnotacaoEnum.LEMBRETE_FINALIZADO.name())){
            throw new RegraDeNegocioException(MSG_EDITAR);
        }

        anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        anotacaoDomainEncontrado.setDataLembrete(toUtc(anotacaoDomainUpdate.getDataLembrete()));
        return updateAnotacaoOutput.updateAnotacao(anotacaoDomainEncontrado);
    }
}
