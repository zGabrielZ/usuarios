package br.com.gabrielferreira.usuarios.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MascaraUtils {

    private MascaraUtils(){}

    private static final Locale BRASIL = new Locale("pt", "BR");

    public static String toCpfFormatado(String cpf){
        if(StringUtils.isNotBlank(cpf) && cpf.length() == 11){
            return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpf;
    }

    public static String toTelefoneFormatado(String ddd, String numero){
        if(StringUtils.isNotBlank(ddd) && StringUtils.isNotBlank(numero)) {
            String numeroCompleto = ddd.concat(numero);
            if (numero.length() == 8) {
                return numeroCompleto.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
            } else if (numero.length() == 9) {
                return numeroCompleto.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
            }
        }
        return null;
    }

    public static String toValorMonetarioBrasil(BigDecimal valor){
        if(valor != null){
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(BRASIL);
            return numberFormat.format(valor);
        }
        return null;
    }
}
