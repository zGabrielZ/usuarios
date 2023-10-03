package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entity.Anotacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

    @Query("SELECT a FROM Anotacao a " +
            "JOIN FETCH a.usuario u " +
            "WHERE a.id = :id")
    Optional<Anotacao> buscarAnotacao(@Param("id") Long id);

    @Query("SELECT a FROM Anotacao a " +
            "JOIN FETCH a.usuario u " +
            "WHERE u.id = :idUsuario")
    Page<Anotacao> buscarAnotacoes(@Param("idUsuario") Long idUsuario, Pageable pageable);

}
