package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.dto.AnotacaoDTO;
import br.com.gabrielferreira.usuario.dto.AnotacaoInsertDTO;
import br.com.gabrielferreira.usuario.entities.Anotacao;
import br.com.gabrielferreira.usuario.entities.Usuario;
import br.com.gabrielferreira.usuario.repository.AnotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.gabrielferreira.usuario.entities.factory.AnotacaoFactory.*;
import static br.com.gabrielferreira.usuario.dto.factory.AnotacaoDTOFactory.*;

@Service
@RequiredArgsConstructor
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;

    private final UsuarioService usuarioService;

    @Transactional
    public AnotacaoDTO cadastrarAnotacao(AnotacaoInsertDTO anotacaoInsertDTO){
        Usuario usuario = usuarioService.buscarUsuario(anotacaoInsertDTO.getUsuario().getId());
        Anotacao anotacao = toAnotacao(usuario, anotacaoInsertDTO);

        anotacao = anotacaoRepository.save(anotacao);
        return toAnotacaoDto(anotacao);
    }
}
