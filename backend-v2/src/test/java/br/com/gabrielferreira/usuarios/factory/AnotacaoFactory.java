package br.com.gabrielferreira.usuarios.factory;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;

public class AnotacaoFactory {

    private AnotacaoFactory(){}

    public static AnotacaoRascunhoCreateDTO createAnotacaoRascunho(String titulo, String descricao){
        return new AnotacaoRascunhoCreateDTO(titulo, descricao);
    }
}
