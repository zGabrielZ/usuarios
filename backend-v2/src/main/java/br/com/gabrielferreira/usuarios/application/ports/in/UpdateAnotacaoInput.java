package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface UpdateAnotacaoInput {

    void finalizarAnotacaoRascunho(Long id, Long idUsuario);

    void reabrirAnotacaoRascunho(Long id, Long idUsuario);

    AnotacaoDomain updateAnotacaoRascunho(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate);

    void finalizarAnotacaoEstudo(Long id, Long idUsuario);

    void reabrirAnotacaoEstudo(Long id, Long idUsuario);

    AnotacaoDomain updateAnotacaoEstudo(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate);

    void finalizarAnotacaoLembrete(Long id, Long idUsuario);

    void reabrirAnotacaoLembrete(Long id, Long idUsuario);

    AnotacaoDomain updateAnotacaoLembrete(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate);
}
