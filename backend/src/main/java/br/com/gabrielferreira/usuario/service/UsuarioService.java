package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.dto.UsuarioDTO;
import br.com.gabrielferreira.usuario.dto.UsuarioInsertDTO;
import br.com.gabrielferreira.usuario.dto.UsuarioUpdateDTO;
import br.com.gabrielferreira.usuario.entities.Genero;
import br.com.gabrielferreira.usuario.entities.TipoTelefone;
import br.com.gabrielferreira.usuario.entities.Usuario;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static br.com.gabrielferreira.usuario.dto.factory.UsuarioDTOFactory.*;
import static br.com.gabrielferreira.usuario.entities.factory.UsuarioFactory.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final TipoTelefoneService tipoTelefoneService;

    private final GeneroService generoService;

    @Transactional
    public UsuarioDTO cadastrarUsuario(UsuarioInsertDTO usuarioInsertDTO){
        Usuario usuario = toUsuario(usuarioInsertDTO);
        usuario = usuarioRepository.save(usuario);
        return toUsuarioDto(usuario);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id){
        return toUsuarioDto(buscarUsuario(id));
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO){
        Usuario usuarioEncontrado = buscarUsuario(id);

        TipoTelefone tipoTelefoneEncontrado = tipoTelefoneService.buscarTipoTelefone(usuarioUpdateDTO.getTelefone().getTipoTelefone().getId());
        Genero generoEncontrado = generoService.buscarGenero(usuarioUpdateDTO.getGenero().getId());

        toUsuario(usuarioEncontrado, tipoTelefoneEncontrado, generoEncontrado, usuarioUpdateDTO);

        usuarioRepository.save(usuarioEncontrado);

        return toUsuarioDto(usuarioEncontrado);
    }

    @Transactional
    public void deletarUsuarioPorId(Long id){
        Usuario usuarioEncontrado = buscarUsuario(id);
        usuarioRepository.delete(usuarioEncontrado);
    }

    public Page<UsuarioDTO> buscarUsuarios(Pageable pageable){
        validarPropriedades(pageable.getSort());
        return toUsuariosDtos(usuarioRepository.buscarUsuarios(pageable));
    }

    private void validarPropriedades(Sort sorts){
        List<String> campos = listarAtributosRecursivamente(new ArrayList<>(), "", UsuarioDTO.class);
        List<String> propriedaes = sorts.stream().map(Sort.Order::getProperty).toList();

        propriedaes.forEach(propriedade -> {
            if(!campos.contains(propriedade)){
                throw new MsgException(String.format("A propriedade informada %s não existe", propriedade));
            }
        });
    }

    private List<String> listarAtributosRecursivamente(List<String> campos, String prefixo, Class<?> classe) {
        for (Field campo : classe.getDeclaredFields()) {
            String nomeCampo = prefixo.concat(campo.getName());
            campos.add(nomeCampo);

            if(!campo.getType().isPrimitive() && !campo.getType().getName().startsWith("java.")){
                campos.remove(nomeCampo);
                listarAtributosRecursivamente(campos, nomeCampo.concat("."), campo.getType());
            }
        }
        return campos;
    }

    private Usuario buscarUsuario(Long id){
        return usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
    }
}
