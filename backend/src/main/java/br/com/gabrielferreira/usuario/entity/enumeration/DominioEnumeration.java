package br.com.gabrielferreira.usuario.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DominioEnumeration {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_BINARIO("Não binário"),
    RESIDENCIAL("Residencial"),
    CELULAR("Celular"),
    RASCUNHO("Rascunho"),
    ESTUDO("Estudo"),
    LEMBRETE("Lembrete"),
    ESTUDO_ANDAMENTO("Estudo em andamento"),
    ESTUDO_FINALIZADO("Estudo finalizado"),
    RASCUNHO_ABERTO("Rascunho em aberto"),
    RASCUNHO_FINALIZADO("Rascunho finalizado"),
    LEMBRETE_ABERTO("Lembrete em aberto"),
    LEMBRETE_FINALIZADO("Lembrete finalizado");

    private final String descricao;

    public static boolean isResidencial(String campo){
        return campo.equals(RESIDENCIAL.name());
    }

    public static boolean isEstudo(String campo){
        return campo.equals(ESTUDO.name());
    }

    public static boolean isLembrete(String campo){
        return campo.equals(LEMBRETE.name());
    }

    public static boolean isRascunho(String campo){
        return campo.equals(RASCUNHO.name());
    }

    public static boolean isCelular(String campo) {
        return campo.equals(CELULAR.name());
    }
}
