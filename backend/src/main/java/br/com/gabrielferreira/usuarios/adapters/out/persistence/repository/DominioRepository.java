package br.com.gabrielferreira.usuarios.adapters.out.persistence.repository;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.DominioEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DominioRepository extends JpaRepository<DominioEntity, Long> {

    @Query("SELECT d FROM DominioEntity d " +
            "JOIN FETCH d.tipo t " +
            "WHERE d.id = :id " +
            "AND t.codigo = :codigo")
    Optional<DominioEntity> findByIdAndTipoCodigo(@Param("id") Long id, @Param("codigo") String codigo);

    @Query("SELECT d FROM DominioEntity d " +
            "JOIN FETCH d.tipo t " +
            "WHERE t.codigo = :codigo")
    List<DominioEntity> findAllByTipoCodigo(@Param("codigo") String codigo);

    @Query("SELECT d FROM DominioEntity d " +
            "JOIN FETCH d.tipo t " +
            "WHERE d.codigo = :codigo " +
            "AND t.codigo = :tipoCodigo")
    Optional<DominioEntity> findByCodigoAndTipoCodigo(@Param("codigo") String codigo, @Param("tipoCodigo") String tipoCodigo);
}
