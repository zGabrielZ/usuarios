package br.com.gabrielferreira.usuario.entities.factory;

import br.com.gabrielferreira.usuario.dto.TelefoneInsertDTO;
import br.com.gabrielferreira.usuario.entities.Telefone;

import static br.com.gabrielferreira.usuario.entities.factory.TipoTelefoneFactory.*;

public class TelefoneFactory {

    private TelefoneFactory(){}

    public static Telefone toTelefone(TelefoneInsertDTO telefoneInsertDTO){
        if(telefoneInsertDTO != null){
            return Telefone.builder()
                    .numero(telefoneInsertDTO.getNumero())
                    .ddd(telefoneInsertDTO.getDdd())
                    .descricao(telefoneInsertDTO.getDescricao())
                    .tipoTelefone(toTipoTelefone(telefoneInsertDTO.getTipoTelefone()))
                    .build();
        }
        return null;
    }
}
