package br.com.gabrielferreira.usuarios.adapters.out.persistence.repository;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnotacaoRepository extends JpaRepository<AnotacaoEntity, Long> {

    @Query("SELECT a FROM AnotacaoEntity a " +
            "JOIN FETCH a.usuario u " +
            "JOIN FETCH a.tipoAnotacao ta " +
            "JOIN FETCH ta.tipo tat " +
            "JOIN FETCH a.situacaoTipoAnotacao sta " +
            "JOIN FETCH sta.tipo stat " +
            "WHERE a.id = :id " +
            "AND u.id = :idUsuario " +
            "AND ta.codigo = :codigo")
    Optional<AnotacaoEntity> findByIdAndIdUsuarioAndTipoAnotacao(@Param("id") Long id, @Param("idUsuario") Long idUsuario, @Param("codigo") String codigo);
}
