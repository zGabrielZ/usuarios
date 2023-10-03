package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.entity.Usuario;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.domain.UsuarioDomainMapper;
import br.com.gabrielferreira.usuario.mapper.entity.UsuarioMapper;
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

    private final UsuarioDomainMapper usuarioDomainMapper;

    @Transactional
    public UsuarioDomain cadastrarUsuario(UsuarioDomain usuarioDomain){
        usuarioDomain.getTelefone().setTipoTelefone(tipoTelefoneService.buscarTipoTelefonePorId(usuarioDomain.getTelefone().getTipoTelefone().getId()));
        usuarioDomain.setGenero(generoService.buscarGeneroPorId(usuarioDomain.getGenero().getId()));

        Usuario usuario = usuarioMapper.toUsuario(usuarioDomain);
        usuario = usuarioRepository.save(usuario);
        return usuarioDomainMapper.toUsuarioDomain(usuario);
    }

    public UsuarioDomain buscarUsuarioPorId(Long id){
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
        return usuarioDomainMapper.toUsuarioDomain(usuario);
    }

    @Transactional
    public UsuarioDomain atualizarUsuario(UsuarioDomain usuarioDomain){
        UsuarioDomain usuarioDomainEncontrado = buscarUsuarioPorId(usuarioDomain.getId());
        usuarioDomainEncontrado.getTelefone().setTipoTelefone(tipoTelefoneService.buscarTipoTelefonePorId(usuarioDomain.getTelefone().getTipoTelefone().getId()));
        usuarioDomainEncontrado.setGenero(generoService.buscarGeneroPorId(usuarioDomain.getGenero().getId()));

        usuarioDomainMapper.updateUsuarioDomain(usuarioDomainEncontrado, usuarioDomain);

        Usuario usuario = usuarioMapper.toUsuario(usuarioDomainEncontrado);
        usuario = usuarioRepository.save(usuario);
        return usuarioDomainMapper.toUsuarioDomain(usuario);
    }

    @Transactional
    public void deletarUsuarioPorId(Long id){
        UsuarioDomain usuarioDomainEncontrado = buscarUsuarioPorId(id);
        usuarioRepository.deleteById(usuarioDomainEncontrado.getId());
    }

    public Page<UsuarioDomain> buscarUsuarios(Pageable pageable){
        return usuarioDomainMapper.toUsuariosDomains(usuarioRepository.buscarUsuarios(pageable));
    }
}
