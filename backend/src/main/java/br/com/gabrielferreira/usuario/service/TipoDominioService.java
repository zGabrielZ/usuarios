package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.TipoDominioDomain;
import br.com.gabrielferreira.usuario.entity.TipoDominio;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.TipoDominioMapper;
import br.com.gabrielferreira.usuario.repository.TipoDominioRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDominioService {

    private final TipoDominioRepository tipoDominioRepository;

    private final TipoDominioMapper tipoDominioMapper;

    public List<TipoDominioDomain> buscarTipoDominios(){
        List<TipoDominio> tipoDominios = tipoDominioRepository.buscarTipoDominios();
        return tipoDominioMapper.toTipoDominiosDomains(tipoDominios);
    }

    public TipoDominioDomain buscarTipoDominioPorId(Long id){
        TipoDominio tipoDominio = tipoDominioRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Tipo Domínio não encontrado"));
        return tipoDominioMapper.toTipoDominioDomain(tipoDominio);
    }

    public TipoDominioDomain buscarTipoDominioPorCodigo(String codigo){
        if(StringUtils.isBlank(codigo)){
            throw new MsgException("É necessário informar o código");
        }

        TipoDominio tipoDominio = tipoDominioRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new NaoEncontradoException("Tipo Domínio não encontrado"));
        return tipoDominioMapper.toTipoDominioDomain(tipoDominio);
    }
}
