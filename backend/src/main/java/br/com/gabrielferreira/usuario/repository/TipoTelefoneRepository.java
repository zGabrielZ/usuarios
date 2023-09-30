package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entity.TipoTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Long> {

    @Query("SELECT t FROM TipoTelefone t ORDER BY t.descricao ASC")
    List<TipoTelefone> buscarTiposTelefones();

    @Query("SELECT t FROM TipoTelefone t WHERE t.codigo = :codigo")
    Optional<TipoTelefone> buscarPorCodigo(@Param("codigo") String codigo);
}
