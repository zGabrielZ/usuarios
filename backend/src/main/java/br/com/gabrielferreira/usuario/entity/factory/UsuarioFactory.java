package br.com.gabrielferreira.usuario.entity.factory;

import br.com.gabrielferreira.usuario.dto.request.UsuarioCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.UsuarioUpdateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Genero;
import br.com.gabrielferreira.usuario.entity.TipoTelefone;
import br.com.gabrielferreira.usuario.entity.Usuario;

import static br.com.gabrielferreira.usuario.entity.factory.TelefoneFactory.*;

public class UsuarioFactory {

    private UsuarioFactory(){}

    public static Usuario toUsuario(TipoTelefone tipoTelefone, Genero genero, UsuarioCreateRequestDTO usuarioCreateRequestDTO){
        if(usuarioCreateRequestDTO != null){
            return Usuario.builder()
                    .nome(usuarioCreateRequestDTO.getNome())
                    .email(usuarioCreateRequestDTO.getEmail())
                    .cpf(usuarioCreateRequestDTO.getCpf())
                    .renda(usuarioCreateRequestDTO.getRenda())
                    .dataNascimento(usuarioCreateRequestDTO.getDataNascimento())
                    .quantidadeFilhos(usuarioCreateRequestDTO.getQuantidadeFilhos())
                    .telefone(toTelefone(tipoTelefone, usuarioCreateRequestDTO.getTelefone()))
                    .genero(genero)
                    .build();
        }
        return null;
    }

    public static void toUsuario(Usuario usuario, TipoTelefone tipoTelefone, Genero genero, UsuarioUpdateRequestDTO usuarioUpdateRequestDTO){
        if(usuario != null && usuarioUpdateRequestDTO != null){
            usuario.setNome(usuarioUpdateRequestDTO.getNome());
            usuario.setRenda(usuarioUpdateRequestDTO.getRenda());
            usuario.setQuantidadeFilhos(usuarioUpdateRequestDTO.getQuantidadeFilhos());
            toTelefone(usuario.getTelefone(), tipoTelefone, usuarioUpdateRequestDTO.getTelefone());
            usuario.setGenero(genero);
        }
    }
}
