package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entity.TipoDominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoDominioRepository extends JpaRepository<TipoDominio, Long> {

    @Query("SELECT td FROM TipoDominio td ORDER BY td.descricao ASC")
    List<TipoDominio> buscarTipoDominios();

    @Query("SELECT td FROM TipoDominio td WHERE td.codigo = :codigo")
    Optional<TipoDominio> buscarPorCodigo(@Param("codigo") String codigo);
}
