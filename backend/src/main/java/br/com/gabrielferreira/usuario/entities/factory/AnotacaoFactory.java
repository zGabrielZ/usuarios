package br.com.gabrielferreira.usuario.entities.factory;

import br.com.gabrielferreira.usuario.dto.AnotacaoInsertDTO;
import br.com.gabrielferreira.usuario.entities.Anotacao;
import br.com.gabrielferreira.usuario.entities.Usuario;

public class AnotacaoFactory {

    private AnotacaoFactory(){}

    public static Anotacao toAnotacao(Usuario usuario, AnotacaoInsertDTO anotacaoInsertDTO){
        if(anotacaoInsertDTO != null){
            return Anotacao.builder()
                    .descricao(anotacaoInsertDTO.getDescricao())
                    .usuario(usuario)
                    .build();
        }
        return null;
    }
}
