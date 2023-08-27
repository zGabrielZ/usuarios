package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.dto.UsuarioDTO;
import br.com.gabrielferreira.usuario.dto.UsuarioInsertDTO;
import br.com.gabrielferreira.usuario.entities.Usuario;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.gabrielferreira.usuario.dto.factory.UsuarioDTOFactory.*;
import static br.com.gabrielferreira.usuario.entities.factory.UsuarioFactory.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO cadastrarUsuario(UsuarioInsertDTO usuarioInsertDTO){
        Usuario usuario = toUsuario(usuarioInsertDTO);
        usuario = usuarioRepository.save(usuario);
        return toUsuarioDto(usuario);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id){
        return toUsuarioDto(buscarUsuario(id));
    }

    private Usuario buscarUsuario(Long id){
        return usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
    }
}
