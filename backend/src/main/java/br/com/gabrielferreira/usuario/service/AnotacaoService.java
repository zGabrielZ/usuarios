package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.AnotacaoMapper;
import br.com.gabrielferreira.usuario.repository.AnotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;

    private final UsuarioService usuarioService;

    private final AnotacaoMapper anotacaoMapper;

    @Transactional
    public AnotacaoDomain cadastrarAnotacao(AnotacaoDomain anotacaoDomain){
        anotacaoDomain.setUsuario(usuarioService.buscarUsuarioPorId(anotacaoDomain.getUsuario().getId()));

        Anotacao anotacao = anotacaoMapper.toAnotacao(anotacaoDomain);
        anotacao = anotacaoRepository.save(anotacao);
        return anotacaoMapper.toAnotacaoDomainWithoutUsuario(anotacao);
    }

    public AnotacaoDomain buscarAnotacaoPorId(Long id){
        Anotacao anotacao = anotacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Anotação não encontrada"));
        return anotacaoMapper.toAnotacaoDomainWithoutUsuario(anotacao);
    }

    @Transactional
    public AnotacaoDomain atualizarAnotacao(AnotacaoDomain anotacaoDomain){
        AnotacaoDomain anotacaoDomainEncontrada = anotacaoMapper
                .toAnotacaoDomainWithUsuario(anotacaoRepository.buscarAnotacao(anotacaoDomain.getId())
                .orElseThrow(() -> new NaoEncontradoException("Anotação não encontrada")));

        updateAnotacaoDomain(anotacaoDomainEncontrada, anotacaoDomain);

        Anotacao anotacao = anotacaoMapper.toAnotacao(anotacaoDomainEncontrada);
        anotacao = anotacaoRepository.save(anotacao);
        return anotacaoMapper.toAnotacaoDomainWithoutUsuario(anotacao);
    }

    @Transactional
    public void deletarAnotacao(Long id){
        AnotacaoDomain anotacaoDomainEncontrada = buscarAnotacaoPorId(id);
        anotacaoRepository.deleteById(anotacaoDomainEncontrada.getId());
    }

    public Page<AnotacaoDomain> buscarAnotacoes(Long idUsuario, Pageable pageable){
        Page<Anotacao> anotacoes = anotacaoRepository.buscarAnotacoes(idUsuario, pageable);
        return anotacaoMapper.toAnotacoesDomains(anotacoes);
    }

    private void updateAnotacaoDomain(AnotacaoDomain anotacaoDomainEncontrado, AnotacaoDomain anotacaoDomainUpdate){
        if(anotacaoDomainEncontrado != null && anotacaoDomainUpdate != null){
            anotacaoDomainEncontrado.setTitulo(anotacaoDomainUpdate.getTitulo());
            anotacaoDomainEncontrado.setDescricao(anotacaoDomainUpdate.getDescricao());
        }
    }
}
