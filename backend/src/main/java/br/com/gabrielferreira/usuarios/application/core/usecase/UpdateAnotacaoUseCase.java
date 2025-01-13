package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.exception.ForbiddenException;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateAnotacaoOutput;

public class UpdateAnotacaoUseCase implements UpdateAnotacaoInput {

    private final UpdateAnotacaoOutput updateAnotacaoOutput;

    private final FindAnotacaoInput findAnotacaoInput;

    private final FindSituacaoAnotacaoInput findSituacaoAnotacaoInput;

    private final ValidCreateAnotacaoInput validCreateAnotacaoInput;

    private final UserCurrentInput userCurrentInput;

    public UpdateAnotacaoUseCase(UpdateAnotacaoOutput updateAnotacaoOutput,
                                 FindAnotacaoInput findAnotacaoInput,
                                 FindSituacaoAnotacaoInput findSituacaoAnotacaoInput,
                                 ValidCreateAnotacaoInput validCreateAnotacaoInput,
                                 UserCurrentInput userCurrentInput) {
        this.updateAnotacaoOutput = updateAnotacaoOutput;
        this.findAnotacaoInput = findAnotacaoInput;
        this.findSituacaoAnotacaoInput = findSituacaoAnotacaoInput;
        this.validCreateAnotacaoInput = validCreateAnotacaoInput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public void finalizarAnotacaoRascunho(Long id, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoFinalizada(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.RASCUNHO_FINALIZADO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_FINALIZADO.name());

        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);

        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void reabrirAnotacaoRascunho(Long id, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoAberto(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.RASCUNHO_ABERTO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.RASCUNHO_ABERTO.name());

        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);

        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacaoRascunho(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarSituacaoEditar(anotacaoDomainEncontrado.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.RASCUNHO_FINALIZADO);

        anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());

        return updateAnotacaoOutput.updateAnotacao(anotacaoDomainEncontrado);
    }

    @Override
    public void finalizarAnotacaoEstudo(Long id, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoFinalizada(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.ESTUDO_FINALIZADO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_FINALIZADO.name());

        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);

        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void reabrirAnotacaoEstudo(Long id, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoAberto(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.ESTUDO_ANDAMENTO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.ESTUDO_ANDAMENTO.name());

        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);

        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacaoEstudo(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarDataInicioDataFimEstudo(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarSituacaoEditar(anotacaoDomainEncontrado.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.ESTUDO_FINALIZADO);

        anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        anotacaoDomainEncontrado.setDataEstudoInicio(anotacaoDomainUpdate.getDataEstudoInicio());
        anotacaoDomainEncontrado.setDataEstudoFim(anotacaoDomainUpdate.getDataEstudoFim());

        return updateAnotacaoOutput.updateAnotacao(anotacaoDomainEncontrado);
    }

    @Override
    public void finalizarAnotacaoLembrete(Long id, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoFinalizada(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.LEMBRETE_FINALIZADO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE_FINALIZADO.name());

        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);

        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public void reabrirAnotacaoLembrete(Long id, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        validCreateAnotacaoInput.validarSituacaoAberto(anotacaoDomain.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.LEMBRETE_ABERTO);

        DominioDomain situacaoTipoAnotacao = findSituacaoAnotacaoInput.findByCodigo(TipoAnotacaoEnum.LEMBRETE_ABERTO.name());

        anotacaoDomain.setSituacaoTipoAnotacao(situacaoTipoAnotacao);

        updateAnotacaoOutput.updateAnotacao(anotacaoDomain);
    }

    @Override
    public AnotacaoDomain updateAnotacaoLembrete(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate) {
        validarAdminOuProprioUsuario(idUsuario);
        AnotacaoDomain anotacaoDomainEncontrado = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);

        validCreateAnotacaoInput.validarCampos(anotacaoDomainUpdate);
        validCreateAnotacaoInput.validarSituacaoEditar(anotacaoDomainEncontrado.getSituacaoTipoAnotacao(), TipoAnotacaoEnum.LEMBRETE_FINALIZADO);

        anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        anotacaoDomainEncontrado.setDataLembrete(anotacaoDomainUpdate.getDataLembrete());

        return updateAnotacaoOutput.updateAnotacao(anotacaoDomainEncontrado);
    }

    private void validarAdminOuProprioUsuario(Long idUsuario){
        UsuarioDomain usuario = userCurrentInput.getUserCurrent();
        if(!usuario.getId().equals(idUsuario) && usuario.isNaoContemPerfil(RoleEnum.ROLE_ADMIN)){
            throw new ForbiddenException("Você não tem a permissão de realizar esta atualização");
        }
    }
}
