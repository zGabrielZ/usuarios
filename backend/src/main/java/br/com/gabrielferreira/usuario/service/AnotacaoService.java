package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.domain.AnotacaoDomainMapper;
import br.com.gabrielferreira.usuario.mapper.entity.AnotacaoMapper;
import br.com.gabrielferreira.usuario.repository.AnotacaoRepository;
import br.com.gabrielferreira.usuario.repository.projection.AnotacaoResumidaProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AnotacaoService {

    private static final String MSG_NAO_ENCONTRADA = "Anotação não encontrada";

    private final AnotacaoRepository anotacaoRepository;

    private final UsuarioService usuarioService;

    private final AnotacaoDomainMapper anotacaoDomainMapper;

    private final AnotacaoMapper anotacaoMapper;

    @Transactional
    public AnotacaoDomain cadastrarAnotacao(AnotacaoDomain anotacaoDomain){
        anotacaoDomain.setUsuario(usuarioService.buscarUsuarioPorId(anotacaoDomain.getUsuario().getId()));

        Anotacao anotacao = anotacaoMapper.toAnotacao(anotacaoDomain);
        anotacao = anotacaoRepository.save(anotacao);
        return anotacaoDomainMapper.toAnotacaoDomain(anotacao);
    }

    public AnotacaoDomain buscarAnotacaoPorId(Long id){
        Anotacao anotacao = anotacaoRepository.buscarAnotacaoPorId(id)
                .orElseThrow(() -> new NaoEncontradoException(MSG_NAO_ENCONTRADA));
        return anotacaoDomainMapper.toAnotacaoDomain(anotacao);
    }

    public AnotacaoDomain buscarAnotacaoResumidaPorId(Long id){
        AnotacaoResumidaProjection anotacaoResumidaProjection = anotacaoRepository.buscarAnotacaoResumidoPorId(id)
                .orElseThrow(() -> new NaoEncontradoException(MSG_NAO_ENCONTRADA));
        return anotacaoDomainMapper.toAnotacaoDomain(anotacaoResumidaProjection);
    }

    @Transactional
    public AnotacaoDomain atualizarAnotacao(AnotacaoDomain anotacaoDomain){
        AnotacaoDomain anotacaoDomainEncontrada = buscarAnotacaoPorId(anotacaoDomain.getId());

        anotacaoDomainMapper.updateAnotacaoDomain(anotacaoDomainEncontrada, anotacaoDomain);

        Anotacao anotacao = anotacaoMapper.toAnotacao(anotacaoDomainEncontrada);
        anotacao = anotacaoRepository.save(anotacao);
        return anotacaoDomainMapper.toAnotacaoDomain(anotacao);
    }

    @Transactional
    public void deletarAnotacao(Long id){
        AnotacaoDomain anotacaoDomainEncontrada = buscarAnotacaoPorId(id);
        anotacaoRepository.deleteById(anotacaoDomainEncontrada.getId());
    }

    public Page<AnotacaoDomain> buscarAnotacoes(Long idUsuario, Pageable pageable){
        Page<Anotacao> anotacoes = anotacaoRepository.buscarAnotacoes(idUsuario, pageable);
        return anotacaoDomainMapper.toAnotacoesDomains(anotacoes);
    }
}
