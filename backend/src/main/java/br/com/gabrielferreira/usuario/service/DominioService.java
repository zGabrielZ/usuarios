package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.entity.Dominio;
import br.com.gabrielferreira.usuario.entity.enumeration.TipoDominioEnumeration;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.DominioRepository;
import br.com.gabrielferreira.usuario.repository.projection.DominioProjection;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

import static br.com.gabrielferreira.usuario.factory.domain.DominioDomainFactory.*;

@Service
@RequiredArgsConstructor
public class DominioService {

    private final DominioRepository dominioRepository;

    private final JdbcTemplate jdbcTemplate;

    public List<DominioDomain> buscarDominios(Long idTipoDominio, String codigoDominio){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select td.id as id, td.codigo as codigo, td.descricao as descricao, ttd.id as idTipoDominio, ttd.codigo as codigoTipoDominio, " +
                "ttd.descricao as descricaoTipoDominio " +
                "from tb_dominio as td join tb_tipo_dominio as ttd on ttd.id = td.id_tipo_dominio " +
                "where 1=1 ");

        if(idTipoDominio != null){
            stringBuilder.append("and ttd.id = ").append(idTipoDominio).append(" ");
        }

        if(!StringUtils.isBlank(codigoDominio)){
            stringBuilder.append("and ttd.codigo = ").append("'").append(codigoDominio).append("'").append(" ");
        }

        stringBuilder.append("order by td.descricao ASC");

        List<DominioProjection> dominioProjections = jdbcTemplate.query(stringBuilder.toString(), new BeanPropertyRowMapper<>(DominioProjection.class));
        return toDominiosDomains(dominioProjections);
    }

    public DominioDomain buscarDominioPorId(Long id){
        Dominio dominio = dominioRepository.buscarDominioPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Domínio não encontrado"));
        return toDominioDomain(dominio);
    }

    public DominioDomain buscarDominioPorCodigo(String codigo){
        if(StringUtils.isBlank(codigo)){
            throw new MsgException("É necessário informar o código");
        }

        Dominio dominio = dominioRepository.buscarDominioPorCodigo(codigo)
                .orElseThrow(() -> new NaoEncontradoException("Domínio não encontrado"));
        return toDominioDomain(dominio);
    }

    public DominioDomain buscarDominioPorIdPorCodigoTipoDominio(Long id, TipoDominioEnumeration tipoDominioEnumeration){
        Dominio dominio = dominioRepository.buscarDominioPorIdPorCodigoTipoDominio(id, tipoDominioEnumeration.name())
                .orElseThrow(() -> new NaoEncontradoException(String.format("%s não encontrado", tipoDominioEnumeration.getDescricao())));
        return toDominioDomain(dominio);
    }
}
