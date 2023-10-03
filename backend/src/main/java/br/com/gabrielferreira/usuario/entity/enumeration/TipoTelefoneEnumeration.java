package br.com.gabrielferreira.usuario.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoTelefoneEnumeration {

    RESIDENCIAL, CELULAR;

    public static boolean isResidencial(String campo){
        return campo.equals(RESIDENCIAL.name());
    }
}
