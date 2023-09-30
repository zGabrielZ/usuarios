package br.com.gabrielferreira.usuario.entity.factory;

import br.com.gabrielferreira.usuario.dto.AnotacaoInsertDTO;
import br.com.gabrielferreira.usuario.dto.AnotacaoUpdateDTO;
import br.com.gabrielferreira.usuario.entity.Anotacao;
import br.com.gabrielferreira.usuario.entity.Usuario;

public class AnotacaoFactory {

    private AnotacaoFactory(){}

    public static Anotacao toAnotacao(Usuario usuario, AnotacaoInsertDTO anotacaoInsertDTO){
        if(anotacaoInsertDTO != null){
            return Anotacao.builder()
                    .titulo(anotacaoInsertDTO.getTitulo())
                    .descricao(anotacaoInsertDTO.getDescricao())
                    .usuario(usuario)
                    .build();
        }
        return null;
    }

    public static void toAnotacao(Anotacao anotacao, AnotacaoUpdateDTO anotacaoUpdateDTO){
        if(anotacao != null && anotacaoUpdateDTO != null){
            anotacao.setTitulo(anotacaoUpdateDTO.getTitulo());
            anotacao.setDescricao(anotacaoUpdateDTO.getDescricao());
        }
    }
}
