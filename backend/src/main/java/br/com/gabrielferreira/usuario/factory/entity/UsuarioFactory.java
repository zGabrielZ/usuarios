package br.com.gabrielferreira.usuario.factory.entity;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.entity.Usuario;

import static br.com.gabrielferreira.usuario.factory.entity.TelefoneFactory.*;
import static br.com.gabrielferreira.usuario.factory.entity.DominioFactory.*;

public class UsuarioFactory {

    private UsuarioFactory(){}

    public static Usuario toUsuario(UsuarioDomain usuarioDomain){
        if(usuarioDomain != null){
            return Usuario.builder()
                    .id(usuarioDomain.getId())
                    .build();
        }
        return null;
    }

    public static Usuario toCreateUsuario(UsuarioDomain usuarioDomain){
        if(usuarioDomain != null){
            return Usuario.builder()
                    .nome(usuarioDomain.getNome())
                    .email(usuarioDomain.getEmail())
                    .cpf(usuarioDomain.getCpf())
                    .renda(usuarioDomain.getRenda())
                    .dataNascimento(usuarioDomain.getDataNascimento())
                    .quantidadeFilhos(usuarioDomain.getQuantidadeFilhos())
                    .telefone(toCreateTelefone(usuarioDomain.getTelefone()))
                    .genero(toDominio(usuarioDomain.getGenero()))
                    .build();
        }
        return null;
    }

    public static Usuario toUpdateUsuario(UsuarioDomain usuarioDomainEncontrado, UsuarioDomain usuarioDomainUpdate){
        if(usuarioDomainEncontrado != null && usuarioDomainUpdate != null){
            return Usuario.builder()
                    .id(usuarioDomainUpdate.getId())
                    .nome(usuarioDomainUpdate.getNome())
                    .email(usuarioDomainEncontrado.getEmail())
                    .cpf(usuarioDomainEncontrado.getCpf())
                    .renda(usuarioDomainUpdate.getRenda())
                    .dataNascimento(usuarioDomainEncontrado.getDataNascimento())
                    .quantidadeFilhos(usuarioDomainUpdate.getQuantidadeFilhos())
                    .telefone(toUpdateTelefone(usuarioDomainEncontrado.getTelefone(), usuarioDomainUpdate.getTelefone()))
                    .genero(toDominio(usuarioDomainUpdate.getGenero()))
                    .createdAt(usuarioDomainEncontrado.getCreatedAt())
                    .build();
        }
        return null;
    }
}
