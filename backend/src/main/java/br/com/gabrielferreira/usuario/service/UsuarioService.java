package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.dto.UsuarioDTO;
import br.com.gabrielferreira.usuario.entities.Usuario;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.gabrielferreira.usuario.dto.factory.UsuarioDTOFactory.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO buscarUsuarioPorId(Long id){
        return toUsuarioDto(buscarUsuario(id));
    }

    private Usuario buscarUsuario(Long id){
        return usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
    }
}
