package br.com.gabrielferreira.usuario.utils;

import br.com.gabrielferreira.usuario.exception.MsgException;
import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MascaraUtils {

    private MascaraUtils(){}

    private static final Locale BRASIL = new Locale("pt", "BR");

    public static String toCpfFormatado(String cpf){
        try {
            MaskFormatter cpfFormatacao = new MaskFormatter("###.###.###-##");
            cpfFormatacao.setValueContainsLiteralCharacters(false);
            return cpfFormatacao.valueToString(cpf);
        } catch (Exception e){
            throw new MsgException("Erro formatação do cpf");
        }
    }

    public static String toTelefoneResidencialFormatado(String numero){
        try {
            MaskFormatter telefoneResidencialFormatacao = new MaskFormatter("(##) ####-####");
            telefoneResidencialFormatacao.setValueContainsLiteralCharacters(false);
            return telefoneResidencialFormatacao.valueToString(numero);
        } catch (Exception e){
            throw new MsgException("Erro formatação do telefone residencial");
        }
    }

    public static String toTelefoneCelularFormatado(String numero){
        try {
            MaskFormatter telefoneCelularFormatacao = new MaskFormatter("(##) #####-####");
            telefoneCelularFormatacao.setValueContainsLiteralCharacters(false);
            return telefoneCelularFormatacao.valueToString(numero);
        } catch (Exception e){
            throw new MsgException("Erro formatação do telefone celular");
        }
    }

    public static String toValorMonetarioBrasil(BigDecimal valor){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(BRASIL);
        return numberFormat.format(valor);
    }
}
