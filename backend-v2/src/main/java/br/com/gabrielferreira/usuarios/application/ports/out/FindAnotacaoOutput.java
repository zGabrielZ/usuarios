package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;

import java.util.List;
import java.util.Optional;

public interface FindAnotacaoOutput {

    Optional<AnotacaoDomain> findByIdAndTipoAnotacaoAndIdUsuario(Long id, String codigoTipoAnotacao, Long idUsuario);

    List<AnotacaoDomain> findAll(PageInfo pageInfo, String titulo, String descricao, Long idUsuario);
}
