package br.com.gabrielferreira.usuario.service.validation;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.repository.UsuarioRepository;
import br.com.gabrielferreira.usuario.service.DominioService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

import static br.com.gabrielferreira.usuario.entity.enumeration.TipoDominioEnumeration.*;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    private final DominioService dominioService;

    public void validarCampos(UsuarioDomain usuarioDomain){
        usuarioDomain.setNome(usuarioDomain.getNome().trim());

        if(!StringUtils.isBlank(usuarioDomain.getEmail())){
            usuarioDomain.setEmail(usuarioDomain.getEmail().trim());
        }

        if(!StringUtils.isBlank(usuarioDomain.getCpf())){
            usuarioDomain.setCpf(usuarioDomain.getCpf().trim());
        }
    }

    public void validarEmailExistente(UsuarioDomain usuarioDomain){
        Optional<Long> idUsuarioEncontrado = usuarioRepository.buscarIdUsuarioPorEmail(usuarioDomain.getEmail());
        if(usuarioDomain.getId() == null && idUsuarioEncontrado.isPresent()){
            throw new MsgException(String.format("Não vai ser possível cadastrar este usuário pois o e-mail '%s' já foi cadastrado", usuarioDomain.getEmail()));
        } else if(usuarioDomain.getId() != null && idUsuarioEncontrado.isPresent() && !usuarioDomain.getId().equals(idUsuarioEncontrado.get())){
            throw new MsgException(String.format("Não vai ser possível atualizar este usuário pois o e-mail '%s' já foi cadastrado", usuarioDomain.getNome()));
        }
    }

    public void validarCpfExistente(UsuarioDomain usuarioDomain){
        Optional<Long> idUsuarioEncontrado = usuarioRepository.buscarIdUsuarioPorCpf(usuarioDomain.getCpf());
        if(usuarioDomain.getId() == null && idUsuarioEncontrado.isPresent()){
            throw new MsgException(String.format("Não vai ser possível cadastrar este usuário pois o CPF '%s' já foi cadastrado", usuarioDomain.getCpfFormatado()));
        } else if(usuarioDomain.getId() != null && idUsuarioEncontrado.isPresent() && !usuarioDomain.getId().equals(idUsuarioEncontrado.get())){
            throw new MsgException(String.format("Não vai ser possível atualizar este usuário pois o CPF '%s' já foi cadastrado", usuarioDomain.getCpfFormatado()));
        }
    }

    public void validarGenero(UsuarioDomain usuarioDomain){
        DominioDomain genero = dominioService
                .buscarDominioPorIdPorCodigoTipoDominio(usuarioDomain.getGenero().getId(), GENERO);
        usuarioDomain.setGenero(genero);
    }
}
