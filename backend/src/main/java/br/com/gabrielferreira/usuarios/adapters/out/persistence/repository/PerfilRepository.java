package br.com.gabrielferreira.usuarios.adapters.out.persistence.repository;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.PerfilEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {

    List<PerfilEntity> findAllByOrderByTitulo();

    Optional<PerfilEntity> findByAutoriedade(String autoriedade);

    @Query("SELECT p FROM UsuarioEntity u " +
            "JOIN u.perfis p " +
            "WHERE p.id = :id AND u.id = :idUsuario")
    Optional<PerfilEntity> findByIdAndIdUsuario(@Param("id") Long id, @Param("idUsuario") Long idUsuario);

    @Query("SELECT p FROM UsuarioEntity u " +
            "JOIN u.perfis p " +
            "WHERE u.id = :idUsuario " +
            "ORDER BY p.titulo")
    List<PerfilEntity> findAllByIdUsuario(@Param("idUsuario") Long idUsuario);
}
