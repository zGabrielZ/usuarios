package br.com.gabrielferreira.usuarios.adapters.out.persistence.repository;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {

    List<PerfilEntity> findAllByOrderByTitulo();

    Optional<PerfilEntity> findByAutoriedade(String autoriedade);
}
