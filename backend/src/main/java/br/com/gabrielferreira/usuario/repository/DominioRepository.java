package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entity.Dominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DominioRepository extends JpaRepository<Dominio, Long> {

    @Query("SELECT d FROM Dominio d " +
            "JOIN FETCH d.tipoDominio td " +
            "WHERE d.id = :id")
    Optional<Dominio> buscarDominioPorId(@Param("id") Long id);

    @Query("SELECT d FROM Dominio d " +
            "JOIN FETCH d.tipoDominio td " +
            "WHERE d.codigo = :codigo")
    Optional<Dominio> buscarDominioPorCodigo(@Param("codigo") String codigo);

}
