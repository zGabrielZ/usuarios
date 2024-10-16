package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindSituacaoAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.AnotacaoMapperOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateAnotacaoOutput;

public class UpdateAnotacaoUseCase implements UpdateAnotacaoInput {

    private final UpdateAnotacaoOutput updateAnotacaoOutput;

    private final FindAnotacaoInput findAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final ValidCreateAnotacaoInput validCreateAnotacaoInput;

    private final AnotacaoMapperOutput anotacaoMapperOutput;

    public UpdateAnotacaoUseCase(UpdateAnotacaoOutput updateAnotacaoOutput,
                                 FindAnotacaoInput findAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                 ValidCreateAnotacaoInput validCreateAnotacaoInput,
                                 AnotacaoMapperOutput anotacaoMapperOutput) {
        this.updateAnotacaoOutput = updateAnotacaoOutput;
        this.findAnotacaoInput = findAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.validCreateAnotacaoInput = validCreateAnotacaoInput;
        this.anotacaoMapperOutput = anotacaoMapperOutput;
    }

    @Override
    public void finalizarAnotacaoRascunho(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoFinalizada(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.RASCUNHO_FINALIZADO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_FINALIZADO.name());
        AnotacaoDomain anotacaoDomainUpdate = anotacaoMapperOutput.updateFinalizarReabrir(anotacaoDomain, situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomainUpdate);
    }

    @Override
    public void reabrirAnotacaoRascunho(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoAberto(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.RASCUNHO_ABERTO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_ABERTO.name());
        AnotacaoDomain anotacaoDomainUpdate = anotacaoMapperOutput.updateFinalizarReabrir(anotacaoDomain, situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomainUpdate);
    }

    @Override
    public AnotacaoDomain updateAnotacaoRascunho(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarSituacaoEditar(anotacaoDomainEncontrado.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.RASCUNHO_FINALIZADO);

        AnotacaoDomain anotacaoDomain = anotacaoMapperOutput.updateAnotacao(anotacaoDomainEncontrado, anotacaoDomainUpdate);
        return updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void finalizarAnotacaoEstudo(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoFinalizada(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.ESTUDO_FINALIZADO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_FINALIZADO.name());
        AnotacaoDomain anotacaoDomainUpdate = anotacaoMapperOutput.updateFinalizarReabrir(anotacaoDomain, situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomainUpdate);
    }

    @Override
    public void reabrirAnotacaoEstudo(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoAberto(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.ESTUDO_ANDAMENTO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_ANDAMENTO.name());
        AnotacaoDomain anotacaoDomainUpdate = anotacaoMapperOutput.updateFinalizarReabrir(anotacaoDomain, situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomainUpdate);
    }

    @Override
    public AnotacaoDomain updateAnotacaoEstudo(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarDataInicioDataFimEstudo(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarSituacaoEditar(anotacaoDomainEncontrado.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.ESTUDO_FINALIZADO);

        AnotacaoDomain anotacaoDomain = anotacaoMapperOutput.updateAnotacao(anotacaoDomainEncontrado, anotacaoDomainUpdate);
        return updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void finalizarAnotacaoLembrete(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoFinalizada(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.LEMBRETE_FINALIZADO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE_FINALIZADO.name());
        AnotacaoDomain anotacaoDomainUpdate = anotacaoMapperOutput.updateFinalizarReabrir(anotacaoDomain, situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomainUpdate);
    }

    @Override
    public void reabrirAnotacaoLembrete(Long id, Long idUsuario) {
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoAberto(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.LEMBRETE_ABERTO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE_ABERTO.name());
        AnotacaoDomain anotacaoDomainUpdate = anotacaoMapperOutput.updateFinalizarReabrir(anotacaoDomain, situacaoTipoAnotacao);
        updateAnotacaoOutput.updateAnotacao(anotacaoDomainUpdate);
    }

    @Override
    public AnotacaoDomain updateAnotacaoLembrete(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarSituacaoEditar(anotacaoDomainEncontrado.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.LEMBRETE_FINALIZADO);

        AnotacaoDomain anotacaoDomain = anotacaoMapperOutput.updateAnotacao(anotacaoDomainEncontrado, anotacaoDomainUpdate);
        return updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }
}
