package br.com.gabrielferreira.usuario.entities.factory;

import br.com.gabrielferreira.usuario.dto.UsuarioInsertDTO;
import br.com.gabrielferreira.usuario.entities.Usuario;

import static br.com.gabrielferreira.usuario.entities.factory.TelefoneFactory.*;
import static br.com.gabrielferreira.usuario.entities.factory.GeneroFactory.*;

public class UsuarioFactory {

    private UsuarioFactory(){}

    public static Usuario toUsuario(UsuarioInsertDTO usuarioInsertDTO){
        if(usuarioInsertDTO != null){
            return Usuario.builder()
                    .nome(usuarioInsertDTO.getNome())
                    .email(usuarioInsertDTO.getEmail())
                    .cpf(usuarioInsertDTO.getCpf())
                    .renda(usuarioInsertDTO.getRenda())
                    .dataNascimento(usuarioInsertDTO.getDataNascimento())
                    .quantidadeFilhos(usuarioInsertDTO.getQuantidadeFilhos())
                    .telefone(toTelefone(usuarioInsertDTO.getTelefone()))
                    .genero(toGenero(usuarioInsertDTO.getGenero()))
                    .build();
        }
        return null;
    }
}
