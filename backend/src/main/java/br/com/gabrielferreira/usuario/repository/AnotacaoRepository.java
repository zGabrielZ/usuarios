package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entities.Anotacao;
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

}
