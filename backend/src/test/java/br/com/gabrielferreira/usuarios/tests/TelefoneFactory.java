package br.com.gabrielferreira.usuarios.tests;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TipoTelefoneCreateDTO;

public class TelefoneFactory {

    private TelefoneFactory(){}

    public static TelefoneCreateDTO createTelefone(String numero, String ddd, String descricao, Long idTipoTelefone){
        return new TelefoneCreateDTO(numero, ddd, descricao,
                new TipoTelefoneCreateDTO(idTipoTelefone));
    }
}
