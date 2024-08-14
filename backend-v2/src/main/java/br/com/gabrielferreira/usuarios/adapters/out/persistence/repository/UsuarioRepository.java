package br.com.gabrielferreira.usuarios.adapters.out.persistence.repository;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByCpf(String cpf);
}
