package br.com.gabrielferreira.usuarios.adapters.out.persistence.repository;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TelefoneEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long> {

    @Query("SELECT t FROM UsuarioEntity u " +
            "JOIN u.telefone t " +
            "JOIN FETCH t.tipoTelefone tt " +
            "JOIN FETCH tt.tipo tipo " +
            "WHERE u.id = :idUsuario")
    Optional<TelefoneEntity> findByUsuarioId(@Param("idUsuario") Long idUsuario);
}
