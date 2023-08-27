package br.com.gabrielferreira.usuario.entities.factory;

import br.com.gabrielferreira.usuario.dto.TelefoneInsertDTO;
import br.com.gabrielferreira.usuario.entities.Telefone;
import br.com.gabrielferreira.usuario.entities.TipoTelefone;

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

    public static void toTelefone(Telefone telefone, TipoTelefone tipoTelefone, TelefoneInsertDTO telefoneInsertDTO){
        if(telefone != null && telefoneInsertDTO != null){
            telefone.setNumero(telefoneInsertDTO.getNumero());
            telefone.setDdd(telefoneInsertDTO.getDdd());
            telefone.setDescricao(telefoneInsertDTO.getDescricao());
            telefone.setTipoTelefone(tipoTelefone);
        }
    }
}
