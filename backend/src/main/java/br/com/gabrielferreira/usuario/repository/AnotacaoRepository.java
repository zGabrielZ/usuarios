package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entities.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

}
