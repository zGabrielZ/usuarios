package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.entity.Usuario;
import br.com.gabrielferreira.usuario.entity.enumeration.TipoDominioEnumeration;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.UsuarioRepository;
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

    private final DominioService dominioService;

    @Transactional
    public UsuarioDomain cadastrarUsuario(UsuarioDomain usuarioDomain){
        DominioDomain tipoTelefone = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getTelefone().getTipoTelefone().getId(), TipoDominioEnumeration.TIPO_TELEFONE);
        usuarioDomain.getTelefone().setTipoTelefone(tipoTelefone);

        DominioDomain genero = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getGenero().getId(), TipoDominioEnumeration.GENERO);
        usuarioDomain.setGenero(genero);

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
        UsuarioDomain usuarioDomainEncontrado = buscarUsuarioPorId(usuarioDomain.getId());

        DominioDomain tipoTelefone = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getTelefone().getTipoTelefone().getId(), TipoDominioEnumeration.TIPO_TELEFONE);
        usuarioDomainEncontrado.getTelefone().setTipoTelefone(tipoTelefone);

        DominioDomain genero = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getGenero().getId(), TipoDominioEnumeration.GENERO);
        usuarioDomainEncontrado.setGenero(genero);

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
}
