package br.com.gabrielferreira.usuario.entity.factory;

import br.com.gabrielferreira.usuario.dto.request.TelefoneCreateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Telefone;
import br.com.gabrielferreira.usuario.entity.TipoTelefone;

public class TelefoneFactory {

    private TelefoneFactory(){}

    public static Telefone toTelefone(TipoTelefone tipoTelefone, TelefoneCreateRequestDTO telefoneCreateRequestDTO){
        if(telefoneCreateRequestDTO != null){
            return Telefone.builder()
                    .numero(telefoneCreateRequestDTO.getNumero())
                    .ddd(telefoneCreateRequestDTO.getDdd())
                    .descricao(telefoneCreateRequestDTO.getDescricao())
                    .tipoTelefone(tipoTelefone)
                    .build();
        }
        return null;
    }

    public static void toTelefone(Telefone telefone, TipoTelefone tipoTelefone, TelefoneCreateRequestDTO telefoneCreateRequestDTO){
        if(telefone != null && telefoneCreateRequestDTO != null){
            telefone.setNumero(telefoneCreateRequestDTO.getNumero());
            telefone.setDdd(telefoneCreateRequestDTO.getDdd());
            telefone.setDescricao(telefoneCreateRequestDTO.getDescricao());
            telefone.setTipoTelefone(tipoTelefone);
        }
    }
}
