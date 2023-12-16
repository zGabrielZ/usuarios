package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.AnotacaoRepository;
import br.com.gabrielferreira.usuario.service.validation.AnotacaoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.gabrielferreira.usuario.factory.entity.AnotacaoFactory.*;
import static br.com.gabrielferreira.usuario.factory.domain.AnotacaoDomainFactory.*;
import static br.com.gabrielferreira.usuario.utils.PageUtils.propriedadesAnotacao;
import static br.com.gabrielferreira.usuario.utils.PageUtils.*;


@Service
@RequiredArgsConstructor
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;

    private final AnotacaoValidator anotacaoValidator;

    @Transactional
    public AnotacaoDomain cadastrarAnotacao(AnotacaoDomain anotacaoDomain){
        validarAnotacao(anotacaoDomain);

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
        validarAtualizarAnotacao(anotacaoDomain);

        Anotacao anotacaoEncontrada = anotacaoRepository.buscarAnotacaoComUsuario(anotacaoDomain.getId())
                .orElseThrow(() -> new NaoEncontradoException("Anotação não encontrada"));

        AnotacaoDomain anotacaoDomainEncontrada = toAnotacaoComUsuario(anotacaoEncontrada);

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
        validarPropriedades(pageable.getSort(), propriedadesAnotacao());
        Page<Anotacao> anotacoes = anotacaoRepository.buscarAnotacoes(idUsuario, pageable);
        return toAnotacoesDomains(anotacoes);
    }

    private void validarAnotacao(AnotacaoDomain anotacaoDomain){
        anotacaoValidator.validarCampos(anotacaoDomain);
        anotacaoValidator.validarTipoAnotacao(anotacaoDomain);
        anotacaoValidator.validarUsuario(anotacaoDomain);
    }

    private void validarAtualizarAnotacao(AnotacaoDomain anotacaoDomain){
        anotacaoValidator.validarCampos(anotacaoDomain);
        anotacaoValidator.validarTipoAnotacao(anotacaoDomain);
    }
}
