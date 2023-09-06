package br.com.gabrielferreira.usuario.entities.factory;

import br.com.gabrielferreira.usuario.dto.UsuarioInsertDTO;
import br.com.gabrielferreira.usuario.dto.UsuarioUpdateDTO;
import br.com.gabrielferreira.usuario.entities.Genero;
import br.com.gabrielferreira.usuario.entities.TipoTelefone;
import br.com.gabrielferreira.usuario.entities.Usuario;

import static br.com.gabrielferreira.usuario.entities.factory.TelefoneFactory.*;

public class UsuarioFactory {

    private UsuarioFactory(){}

    public static Usuario toUsuario(TipoTelefone tipoTelefone, Genero genero, UsuarioInsertDTO usuarioInsertDTO){
        if(usuarioInsertDTO != null){
            return Usuario.builder()
                    .nome(usuarioInsertDTO.getNome())
                    .email(usuarioInsertDTO.getEmail())
                    .cpf(usuarioInsertDTO.getCpf())
                    .renda(usuarioInsertDTO.getRenda())
                    .dataNascimento(usuarioInsertDTO.getDataNascimento())
                    .quantidadeFilhos(usuarioInsertDTO.getQuantidadeFilhos())
                    .telefone(toTelefone(tipoTelefone, usuarioInsertDTO.getTelefone()))
                    .genero(genero)
                    .build();
        }
        return null;
    }

    public static void toUsuario(Usuario usuario, TipoTelefone tipoTelefone, Genero genero, UsuarioUpdateDTO usuarioUpdateDTO){
        if(usuario != null && usuarioUpdateDTO != null){
            usuario.setNome(usuarioUpdateDTO.getNome());
            usuario.setRenda(usuarioUpdateDTO.getRenda());
            usuario.setQuantidadeFilhos(usuarioUpdateDTO.getQuantidadeFilhos());
            toTelefone(usuario.getTelefone(), tipoTelefone, usuarioUpdateDTO.getTelefone());
            usuario.setGenero(genero);
        }
    }
}
