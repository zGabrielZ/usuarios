package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    @Query("SELECT g FROM Genero g ORDER BY g.descricao ASC")
    List<Genero> buscarGeneros();
}
