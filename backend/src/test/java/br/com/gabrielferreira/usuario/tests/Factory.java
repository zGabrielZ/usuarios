package br.com.gabrielferreira.usuario.tests;

import br.com.gabrielferreira.usuario.dto.AnotacaoInsertDTO;
import br.com.gabrielferreira.usuario.dto.AnotacaoUpdateDTO;
import br.com.gabrielferreira.usuario.dto.AnotacaoUsuarioInsertDTO;

public class Factory {

    private Factory(){}

    public static AnotacaoInsertDTO criarAnotacaoInsert(String descricao, Long idUsuario){
        AnotacaoUsuarioInsertDTO usuario = AnotacaoUsuarioInsertDTO.builder().id(idUsuario).build();
        return AnotacaoInsertDTO.builder()
                .descricao(descricao)
                .usuario(usuario)
                .build();
    }

    public static AnotacaoUpdateDTO criarAnotacaoUpdate(String descricao){
        return AnotacaoUpdateDTO.builder()
                .descricao(descricao)
                .build();
    }
}
