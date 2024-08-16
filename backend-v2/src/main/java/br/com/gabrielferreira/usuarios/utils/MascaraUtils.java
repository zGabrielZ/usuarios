package br.com.gabrielferreira.usuarios.utils;

import br.com.gabrielferreira.usuarios.application.exception.MsgException;

import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MascaraUtils {

    private MascaraUtils(){}

    private static final Locale BRASIL = new Locale("pt", "BR");

    // TODO: ENCONTRAR UM OUTRO JEITO DE FORMATAR MASCARAS ALEM DO MASK FORMATTER
    public static String toCpfFormatado(String cpf){
        try {
            MaskFormatter cpfFormatacao = new MaskFormatter("###.###.###-##");
            cpfFormatacao.setValueContainsLiteralCharacters(false);
            return cpfFormatacao.valueToString(cpf);
        } catch (Exception e){
            throw new MsgException("Erro formatação do cpf");
        }
    }

    // TODO: ENCONTRAR UM OUTRO JEITO DE FORMATAR MASCARAS ALEM DO MASK FORMATTER
    public static String toTelefoneResidencialFormatado(String numero){
        try {
            MaskFormatter telefoneResidencialFormatacao = new MaskFormatter("(##) ####-####");
            telefoneResidencialFormatacao.setValueContainsLiteralCharacters(false);
            return telefoneResidencialFormatacao.valueToString(numero);
        } catch (Exception e){
            throw new MsgException("Erro formatação do telefone residencial");
        }
    }

    // TODO: ENCONTRAR UM OUTRO JEITO DE FORMATAR MASCARAS ALEM DO MASK FORMATTER
    public static String toTelefoneCelularFormatado(String numero){
        try {
            MaskFormatter telefoneCelularFormatacao = new MaskFormatter("(##) #####-####");
            telefoneCelularFormatacao.setValueContainsLiteralCharacters(false);
            return telefoneCelularFormatacao.valueToString(numero);
        } catch (Exception e){
            throw new MsgException("Erro formatação do telefone celular");
        }
    }

    // TODO: ENCONTRAR UM OUTRO JEITO DE FORMATAR MASCARAS ALEM DO MASK FORMATTER
    public static String toValorMonetarioBrasil(BigDecimal valor){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(BRASIL);
        return numberFormat.format(valor);
    }
}
