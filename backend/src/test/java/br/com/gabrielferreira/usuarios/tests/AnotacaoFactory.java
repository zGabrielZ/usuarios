package br.com.gabrielferreira.usuarios.tests;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoEstudoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoLembreteCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;

import java.time.ZonedDateTime;

public class AnotacaoFactory {

    private AnotacaoFactory(){}

    public static AnotacaoRascunhoCreateDTO createAnotacaoRascunho(String titulo, String descricao){
        return new AnotacaoRascunhoCreateDTO(titulo, descricao);
    }

    public static AnotacaoLembreteCreateDTO createAnotacaoLembrete(String titulo, String descricao, ZonedDateTime dataLembrete){
        return new AnotacaoLembreteCreateDTO(titulo, descricao, dataLembrete);
    }

    public static AnotacaoEstudoCreateDTO createAnotacaoEstudo(String titulo, String descricao, ZonedDateTime dataEstudoInicio, ZonedDateTime dataEstudoFim){
        return new AnotacaoEstudoCreateDTO(titulo, descricao, dataEstudoInicio, dataEstudoFim);
    }
}
