package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

public interface UpdateAnotacaoRascunhoInput {

    void finalizarAnotacao(Long id, Long idUsuario);

    void reabrirAnotacao(Long id, Long idUsuario);

    AnotacaoDomain updateAnotacao(Long id, Long idUsuario, AnotacaoDomain anotacaoDomainUpdate);
}
