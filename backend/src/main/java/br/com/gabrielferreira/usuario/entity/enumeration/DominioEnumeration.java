package br.com.gabrielferreira.usuario.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DominioEnumeration {

    RESIDENCIAL("Residencial"), CELULAR("Celular");

    private final String descricao;

    public static boolean isResidencial(String campo){
        return campo.equals(RESIDENCIAL.name());
    }
}
