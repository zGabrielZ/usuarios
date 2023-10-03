package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.TipoAnotacaoDomain;
import br.com.gabrielferreira.usuario.entity.TipoAnotacao;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.TipoAnotacaoMapper;
import br.com.gabrielferreira.usuario.repository.TipoAnotacaoRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoAnotacaoService {

    private final TipoAnotacaoRepository tipoAnotacaoRepository;

    private final TipoAnotacaoMapper tipoAnotacaoMapper;

    public List<TipoAnotacaoDomain> buscarTipoAnotacoes(){
        List<TipoAnotacao> tipoAnotacoes = tipoAnotacaoRepository.buscarTipoAnotacoes();
        return tipoAnotacaoMapper.toTipoAnotacoesDomains(tipoAnotacoes);
    }

    public TipoAnotacaoDomain buscarTipoAnotacaoPorId(Long id){
        TipoAnotacao tipoAnotacao = tipoAnotacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Tipo Anotação não encontrado"));
        return tipoAnotacaoMapper.toTipoAnotacaoDomain(tipoAnotacao);
    }

    public TipoAnotacaoDomain buscarTipoAnotacaoPorCodigo(String codigo){
        if(StringUtils.isBlank(codigo)){
            throw new MsgException("É necessário informar o código");
        }

        TipoAnotacao tipoAnotacao = tipoAnotacaoRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new NaoEncontradoException("Tipo Anotação não encontrado"));
        return tipoAnotacaoMapper.toTipoAnotacaoDomain(tipoAnotacao);
    }
}
