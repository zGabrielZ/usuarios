package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entity.Anotacao;
import br.com.gabrielferreira.usuario.repository.projection.AnotacaoResumidaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

    @Query("SELECT a FROM Anotacao a JOIN FETCH a.usuario u " +
            "JOIN FETCH u.telefone t " +
            "JOIN FETCH t.tipoTelefone tt " +
            "JOIN FETCH u.genero g " +
            "WHERE a.id = :id")
    Optional<Anotacao> buscarAnotacaoPorId(@Param("id") Long id);

    @Query("SELECT a.id as id, a.titulo as titulo, a.descricao as descricao, a.createdAt as createdAt, a.updatedAt as updatedAt FROM Anotacao a " +
            "WHERE a.id = :id")
    Optional<AnotacaoResumidaProjection> buscarAnotacaoResumidoPorId(@Param("id") Long id);

    @Query("SELECT a FROM Anotacao a " +
            "JOIN FETCH a.usuario u " +
            "WHERE u.id = :idUsuario")
    Page<Anotacao> buscarAnotacoes(@Param("idUsuario") Long idUsuario, Pageable pageable);

}
