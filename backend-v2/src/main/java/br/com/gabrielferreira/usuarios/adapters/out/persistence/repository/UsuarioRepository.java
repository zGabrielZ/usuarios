package br.com.gabrielferreira.usuarios.adapters.out.persistence.repository;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByCpf(String cpf);

    @Query("SELECT u FROM UsuarioEntity u " +
            "JOIN FETCH u.telefone t " +
            "JOIN FETCH t.tipoTelefone tt " +
            "JOIN FETCH tt.tipo ttp " +
            "JOIN FETCH u.genero g " +
            "JOIN FETCH g.tipo gtp " +
            "WHERE u.id = :id")
    Optional<UsuarioEntity> findUsuarioById(@Param("id") Long id);
}
