package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;

import java.util.List;

public interface FindAnotacaoInput {

    AnotacaoDomain findByIdTipoAnotacaoRascunho(Long id, Long idUsuario);

    AnotacaoDomain findByIdTipoAnotacaoEstudo(Long id, Long idUsuario);

    AnotacaoDomain findByIdTipoAnotacaoLembrete(Long id, Long idUsuario);

    List<AnotacaoDomain> findAll(PageInfo pageInfo, String titulo, String descricao, Long idUsuario);
}
