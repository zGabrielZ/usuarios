package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entity.TipoAnotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TipoAnotacaoRepository extends JpaRepository<TipoAnotacao, Long> {

    @Query("SELECT ta FROM TipoAnotacao ta ORDER BY ta.descricao ASC")
    List<TipoAnotacao> buscarTipoAnotacoes();

    @Query("SELECT ta FROM TipoAnotacao ta WHERE ta.codigo = :codigo")
    Optional<TipoAnotacao> buscarPorCodigo(@Param("codigo") String codigo);
}
