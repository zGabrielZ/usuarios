package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.entity.Usuario;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.UsuarioRepository;
import br.com.gabrielferreira.usuario.service.validation.TelefoneValidator;
import br.com.gabrielferreira.usuario.service.validation.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

import static br.com.gabrielferreira.usuario.utils.PageUtils.*;
import static br.com.gabrielferreira.usuario.factory.entity.UsuarioFactory.*;
import static br.com.gabrielferreira.usuario.factory.domain.UsuarioDomainFactory.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioValidator usuarioValidator;

    private final TelefoneValidator telefoneValidator;

    @Transactional
    public UsuarioDomain cadastrarUsuario(UsuarioDomain usuarioDomain){
        validacaoUsuario(usuarioDomain);

        Usuario usuario = toCreateUsuario(usuarioDomain);
        usuario = usuarioRepository.save(usuario);
        return toUsuario(usuario);
    }

    public UsuarioDomain buscarUsuarioPorId(Long id){
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
        return toUsuario(usuario);
    }

    @Transactional
    public UsuarioDomain atualizarUsuario(UsuarioDomain usuarioDomain){
        usuarioValidator.validarCampos(usuarioDomain);
        telefoneValidator.validarCampos(usuarioDomain.getTelefone());

        UsuarioDomain usuarioDomainEncontrado = buscarUsuarioPorId(usuarioDomain.getId());

        telefoneValidator.validarTipoTelefone(usuarioDomain.getTelefone());
        usuarioValidator.validarGenero(usuarioDomain);
        telefoneValidator.validarNumeroComTipoTelefone(usuarioDomain.getTelefone(), usuarioDomain.getTelefone().getTipoTelefone());

        Usuario usuario = toUpdateUsuario(usuarioDomainEncontrado, usuarioDomain);
        usuario = usuarioRepository.save(usuario);
        return toUsuario(usuario);
    }

    @Transactional
    public void deletarUsuarioPorId(Long id){
        UsuarioDomain usuarioDomainEncontrado = buscarUsuarioPorId(id);
        usuarioRepository.deleteById(usuarioDomainEncontrado.getId());
    }

    public Page<UsuarioDomain> buscarUsuarios(Pageable pageable){
        pageable = validarOrderBy(pageable, atributoDtoToEntity());
        Page<Usuario> usuarios = usuarioRepository.buscarUsuarios(pageable);
        return toUsuariosDomains(usuarios);
    }

    private Map<String, String> atributoDtoToEntity(){
        Map<String, String> atributoDtoToEntity = new HashMap<>();
        atributoDtoToEntity.put("cpfFormatado", "cpf");
        atributoDtoToEntity.put("rendaFormatada", "renda");
        atributoDtoToEntity.put("telefone.telefoneFormatado", "telefone.numero");
        return atributoDtoToEntity;
    }

    private void validacaoUsuario(UsuarioDomain usuarioDomain){
        usuarioValidator.validarCampos(usuarioDomain);
        usuarioValidator.validarEmailExistente(usuarioDomain);
        usuarioValidator.validarCpfExistente(usuarioDomain);
        usuarioValidator.validarGenero(usuarioDomain);

        telefoneValidator.validarCampos(usuarioDomain.getTelefone());
        telefoneValidator.validarTipoTelefone(usuarioDomain.getTelefone());
        telefoneValidator.validarNumeroComTipoTelefone(usuarioDomain.getTelefone(), usuarioDomain.getTelefone().getTipoTelefone());
    }
}
