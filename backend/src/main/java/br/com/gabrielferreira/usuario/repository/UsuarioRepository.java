package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.telefone t JOIN FETCH t.tipoTelefone tt " +
            "JOIN FETCH tt.tipo td " +
            "JOIN FETCH u.genero g JOIN FETCH g.tipo tdg " +
            "WHERE u.id = :id")
    Optional<Usuario> buscarUsuarioPorId(@Param("id") Long id);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.telefone t JOIN FETCH t.tipoTelefone tt " +
            "JOIN FETCH tt.tipo td " +
            "JOIN FETCH u.genero g JOIN FETCH g.tipo tdg")
    Page<Usuario> buscarUsuarios(Pageable pageable);

    @Query("SELECT u.id as id FROM Usuario u " +
            "WHERE u.email = :email")
    Optional<Long> buscarIdUsuarioPorEmail(@Param("email") String email);

    @Query("SELECT u.id as id FROM Usuario u " +
            "WHERE u.cpf = :cpf")
    Optional<Long> buscarIdUsuarioPorCpf(@Param("cpf") String cpf);

}
