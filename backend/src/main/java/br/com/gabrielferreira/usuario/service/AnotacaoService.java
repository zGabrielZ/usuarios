//package br.com.gabrielferreira.usuario.service;
//
//import br.com.gabrielferreira.usuario.dto.*;
//import br.com.gabrielferreira.usuario.entity.Anotacao;
//import br.com.gabrielferreira.usuario.entity.Usuario;
//import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
//import br.com.gabrielferreira.usuario.repository.AnotacaoRepository;
//import br.com.gabrielferreira.usuario.repository.projection.AnotacaoResumidaProjection;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import static br.com.gabrielferreira.usuario.entity.factory.AnotacaoFactory.*;
//import static br.com.gabrielferreira.usuario.dto.factory.AnotacaoDTOFactory.*;
//import static br.com.gabrielferreira.usuario.utils.PageUtils.*;
//
//@Service
//@RequiredArgsConstructor
//public class AnotacaoService {
//
//    private static final String MSG_NAO_ENCONTRADA = "Anotação não encontrada";
//
//    private final AnotacaoRepository anotacaoRepository;
//
//    private final UsuarioService usuarioService;
//
//    @Transactional
//    public AnotacaoDTO cadastrarAnotacao(AnotacaoInsertDTO anotacaoInsertDTO){
//        Usuario usuario = usuarioService.buscarUsuario(anotacaoInsertDTO.getUsuario().getId());
//        Anotacao anotacao = toAnotacao(usuario, anotacaoInsertDTO);
//
//        anotacao = anotacaoRepository.save(anotacao);
//        return toAnotacaoDto(anotacao);
//    }
//
//    public AnotacaoDTO buscarAnotacaoPorId(Long id){
//        return toAnotacaoDto(buscarAnotacao(id));
//    }
//
//    public AnotacaoResumidaDTO buscarAnotacaoResumidaPorId(Long id){
//        return toAnotacaoResumidaDto(buscarAnotacaoResumido(id));
//    }
//
//    @Transactional
//    public AnotacaoDTO atualizarAnotacao(Long id, AnotacaoUpdateDTO anotacaoUpdateDTO){
//        Anotacao anotacaoEncontrada = buscarAnotacao(id);
//
//        toAnotacao(anotacaoEncontrada, anotacaoUpdateDTO);
//
//        anotacaoEncontrada = anotacaoRepository.save(anotacaoEncontrada);
//
//        return toAnotacaoDto(anotacaoEncontrada);
//    }
//
//    @Transactional
//    public void deletarAnotacao(Long id){
//        Anotacao anotacaoEncontrada = anotacaoRepository.findById(id)
//                        .orElseThrow(() -> new NaoEncontradoException(MSG_NAO_ENCONTRADA));
//        anotacaoRepository.delete(anotacaoEncontrada);
//    }
//
//    public Page<AnotacaoResumidaDTO> buscarAnotacoes(Long idUsuario, Pageable pageable){
//        validarPropriedades(pageable.getSort(), AnotacaoResumidaDTO.class);
//        return toAnotacoesResumidasDtos(anotacaoRepository.buscarAnotacoes(idUsuario, pageable));
//    }
//
//    private Anotacao buscarAnotacaoResumido(Long id){
//        AnotacaoResumidaProjection anotacaoResumidaProjection = anotacaoRepository.buscarAnotacaoResumidoPorId(id)
//                .orElseThrow(() -> new NaoEncontradoException(MSG_NAO_ENCONTRADA));
//        return Anotacao.builder()
//                .id(anotacaoResumidaProjection.getId())
//                .titulo(anotacaoResumidaProjection.getTitulo())
//                .descricao(anotacaoResumidaProjection.getDescricao())
//                .createdAt(anotacaoResumidaProjection.getCreatedAt())
//                .updatedAt(anotacaoResumidaProjection.getUpdatedAt())
//                .build();
//    }
//
//    private Anotacao buscarAnotacao(Long id){
//        return anotacaoRepository.buscarAnotacaoPorId(id)
//                .orElseThrow(() -> new NaoEncontradoException(MSG_NAO_ENCONTRADA));
//    }
//}
