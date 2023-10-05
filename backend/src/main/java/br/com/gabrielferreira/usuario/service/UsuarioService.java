package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.entity.Usuario;
import br.com.gabrielferreira.usuario.entity.enumeration.TipoDominioEnumeration;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.UsuarioMapper;
import br.com.gabrielferreira.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

import static br.com.gabrielferreira.usuario.utils.PageUtils.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final DominioService dominioService;

    private final UsuarioMapper usuarioMapper;

    @Transactional
    public UsuarioDomain cadastrarUsuario(UsuarioDomain usuarioDomain){
        DominioDomain tipoTelefone = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getTelefone().getTipoTelefone().getId(), TipoDominioEnumeration.TIPO_TELEFONE);
        usuarioDomain.getTelefone().setTipoTelefone(tipoTelefone);

        DominioDomain genero = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getGenero().getId(), TipoDominioEnumeration.GENERO);
        usuarioDomain.setGenero(genero);

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

        DominioDomain tipoTelefone = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getTelefone().getTipoTelefone().getId(), TipoDominioEnumeration.TIPO_TELEFONE);
        usuarioDomainEncontrado.getTelefone().setTipoTelefone(tipoTelefone);

        DominioDomain genero = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getGenero().getId(), TipoDominioEnumeration.GENERO);
        usuarioDomainEncontrado.setGenero(genero);

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
        pageable = validarOrderBy(pageable, atributoDtoToEntity());
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

    private Map<String, String> atributoDtoToEntity(){
        Map<String, String> atributoDtoToEntity = new HashMap<>();
        atributoDtoToEntity.put("cpfFormatado", "cpf");
        atributoDtoToEntity.put("rendaFormatada", "renda");
        atributoDtoToEntity.put("telefone.telefoneFormatado", "telefone.numero");
        return atributoDtoToEntity;
    }
}
