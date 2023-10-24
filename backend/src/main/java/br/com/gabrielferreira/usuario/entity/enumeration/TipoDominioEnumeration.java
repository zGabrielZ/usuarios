package br.com.gabrielferreira.usuario.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDominioEnumeration {

    GENERO("Gênero"),
    TIPO_TELEFONE("Tipo de telefone"),
    TIPO_ANOTACAO("Tipo de anotação"),
    SITUACAO_TIPO_ANOTACAO("Situação do tipo de anotação");

    private final String descricao;

    public static boolean isTipoTelefone(String campo){
        return campo.equals(TIPO_TELEFONE.name());
    }
}
