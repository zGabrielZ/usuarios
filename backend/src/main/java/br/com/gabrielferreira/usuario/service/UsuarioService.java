package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.entity.Usuario;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.UsuarioMapper;
import br.com.gabrielferreira.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final TipoTelefoneService tipoTelefoneService;

    private final GeneroService generoService;

    private final UsuarioMapper usuarioMapper;

    @Transactional
    public UsuarioDomain cadastrarUsuario(UsuarioDomain usuarioDomain){
        usuarioDomain.getTelefone().setTipoTelefone(tipoTelefoneService.buscarTipoTelefonePorId(usuarioDomain.getTelefone().getTipoTelefone().getId()));
        usuarioDomain.setGenero(generoService.buscarGeneroPorId(usuarioDomain.getGenero().getId()));

        Usuario usuario = usuarioMapper.toUsuario(usuarioDomain);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toUsuarioWithoutAnotacoesDomain(usuario);
    }

    public UsuarioDomain buscarUsuarioPorId(Long id){
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
        return usuarioMapper.toUsuarioWithoutAnotacoesDomain(usuario);
    }

    @Transactional
    public UsuarioDomain atualizarUsuario(UsuarioDomain usuarioDomain){
        UsuarioDomain usuarioDomainEncontrado = buscarUsuarioPorId(usuarioDomain.getId());
        usuarioDomainEncontrado.getTelefone().setTipoTelefone(tipoTelefoneService.buscarTipoTelefonePorId(usuarioDomain.getTelefone().getTipoTelefone().getId()));
        usuarioDomainEncontrado.setGenero(generoService.buscarGeneroPorId(usuarioDomain.getGenero().getId()));

        updateUsuarioDomain(usuarioDomainEncontrado, usuarioDomain);

        Usuario usuario = usuarioMapper.toUsuario(usuarioDomainEncontrado);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toUsuarioWithoutAnotacoesDomain(usuario);
    }

    @Transactional
    public void deletarUsuarioPorId(Long id){
        UsuarioDomain usuarioDomainEncontrado = buscarUsuarioPorId(id);
        usuarioRepository.deleteById(usuarioDomainEncontrado.getId());
    }

    public Page<UsuarioDomain> buscarUsuarios(Pageable pageable){
        Page<Usuario> usuarios = usuarioRepository.buscarUsuarios(pageable);
        return usuarioMapper.toUsuariosDomains(usuarios);
    }

    private void updateUsuarioDomain(UsuarioDomain usuarioDomainEncontrado, UsuarioDomain usuarioDomainUpdate){
        if(usuarioDomainEncontrado != null && usuarioDomainUpdate != null){
            usuarioDomainEncontrado.setNome(usuarioDomainUpdate.getNome());
            usuarioDomainEncontrado.setRenda(usuarioDomainUpdate.getRenda());
            usuarioDomainEncontrado.setQuantidadeFilhos(usuarioDomainUpdate.getQuantidadeFilhos());
            usuarioDomainEncontrado.getTelefone().setNumero(usuarioDomainUpdate.getTelefone().getNumero());
            usuarioDomainEncontrado.getTelefone().setDdd(usuarioDomainUpdate.getTelefone().getDdd());
            usuarioDomainEncontrado.getTelefone().setDescricao(usuarioDomainUpdate.getTelefone().getDescricao());
        }
    }
}
