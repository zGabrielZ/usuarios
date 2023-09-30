package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    @Query("SELECT g FROM Genero g ORDER BY g.descricao ASC")
    List<Genero> buscarGeneros();

    @Query("SELECT g FROM Genero g WHERE g.codigo = :codigo")
    Optional<Genero> buscarPorCodigo(@Param("codigo") String codigo);
}
