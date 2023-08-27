package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entities.TipoTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Long> {

    @Query("SELECT t FROM TipoTelefone t ORDER BY t.descricao ASC")
    List<TipoTelefone> buscarTiposTelefones();
}
