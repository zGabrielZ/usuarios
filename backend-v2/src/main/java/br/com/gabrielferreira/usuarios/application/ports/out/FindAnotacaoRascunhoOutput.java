package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;

import java.util.Optional;

public interface FindAnotacaoRascunhoOutput {

    Optional<AnotacaoDomain> findByIdAndTipoAnotacaoAndIdUsuario(Long id, String tipoAnotacao, Long idUsuario);
}
