package br.com.gabrielferreira.usuarios.adapters.out.persistence.repository;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.DominioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DominioRepository extends JpaRepository<DominioEntity, Long> {

    Optional<DominioEntity> findByIdAndTipoCodigo(Long id, String codigo);
}
