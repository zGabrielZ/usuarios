package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import br.com.gabrielferreira.usuario.entity.enumeration.DominioEnumeration;
import br.com.gabrielferreira.usuario.entity.enumeration.TipoDominioEnumeration;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.AnotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.gabrielferreira.usuario.factory.entity.AnotacaoFactory.*;
import static br.com.gabrielferreira.usuario.factory.domain.AnotacaoDomainFactory.*;
import static br.com.gabrielferreira.usuario.entity.enumeration.DominioEnumeration.*;


@Service
@RequiredArgsConstructor
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;

    private final UsuarioService usuarioService;

    private final DominioService dominioService;

    @Transactional
    public AnotacaoDomain cadastrarAnotacao(AnotacaoDomain anotacaoDomain){
        anotacaoDomain.setUsuario(usuarioService.buscarUsuarioPorId(anotacaoDomain.getUsuario().getId()));
        validarTipoAnotacao(anotacaoDomain);

        Anotacao anotacao = toCreateAnotacao(anotacaoDomain);
        anotacao = anotacaoRepository.save(anotacao);
        return toAnotacao(anotacao);
    }

    public AnotacaoDomain buscarAnotacaoPorId(Long id){
        Anotacao anotacao = anotacaoRepository.buscarAnotacao(id)
                .orElseThrow(() -> new NaoEncontradoException("Anotação não encontrada"));
        return toAnotacao(anotacao);
    }

    @Transactional
    public AnotacaoDomain atualizarAnotacao(AnotacaoDomain anotacaoDomain){
        Anotacao anotacaoEncontrada = anotacaoRepository.buscarAnotacaoComUsuario(anotacaoDomain.getId())
                .orElseThrow(() -> new NaoEncontradoException("Anotação não encontrada"));

        AnotacaoDomain anotacaoDomainEncontrada = toAnotacaoComUsuario(anotacaoEncontrada);
        validarTipoAnotacao(anotacaoDomain);

        Anotacao anotacao = toUpdateAnotacao(anotacaoDomainEncontrada, anotacaoDomain);
        anotacao = anotacaoRepository.save(anotacao);
        return toAnotacao(anotacao);
    }

    @Transactional
    public void deletarAnotacao(Long id){
        AnotacaoDomain anotacaoDomainEncontrada = buscarAnotacaoPorId(id);
        anotacaoRepository.deleteById(anotacaoDomainEncontrada.getId());
    }

    public Page<AnotacaoDomain> buscarAnotacoes(Long idUsuario, Pageable pageable){
        Page<Anotacao> anotacoes = anotacaoRepository.buscarAnotacoes(idUsuario, pageable);
        return toAnotacoesDomains(anotacoes);
    }

    private void validarTipoAnotacao(AnotacaoDomain anotacaoDomain){
        DominioDomain tipoAnotacao = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(anotacaoDomain.getTipoAnotacao().getId(), TipoDominioEnumeration.TIPO_ANOTACAO);
        anotacaoDomain.setTipoAnotacao(tipoAnotacao);

        if(isEstudo(tipoAnotacao.getCodigo())){
            anotacaoDomain.setDataLembrete(null);
            anotacaoDomain.setSituacaoTipoAnotacao(dominioService.buscarDominioPorCodigo(DominioEnumeration.ESTUDO_ANDAMENTO.name()));
        } else if(isLembrete(tipoAnotacao.getCodigo())){
            anotacaoDomain.setDataEstudoInicio(null);
            anotacaoDomain.setDataEstudoFim(null);
            anotacaoDomain.setSituacaoTipoAnotacao(dominioService.buscarDominioPorCodigo(DominioEnumeration.LEMBRETE_ABERTO.name()));
        } else if(isRascunho(tipoAnotacao.getCodigo())){
            anotacaoDomain.setDataLembrete(null);
            anotacaoDomain.setDataEstudoInicio(null);
            anotacaoDomain.setDataEstudoFim(null);
            anotacaoDomain.setSituacaoTipoAnotacao(dominioService.buscarDominioPorCodigo(DominioEnumeration.RASCUNHO_ABERTO.name()));
        }
    }
}
