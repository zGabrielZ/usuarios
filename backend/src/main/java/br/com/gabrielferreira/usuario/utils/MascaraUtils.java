package br.com.gabrielferreira.usuario.utils;

import br.com.gabrielferreira.usuario.exception.MsgException;

import javax.swing.text.MaskFormatter;

public class MascaraUtils {

    private MascaraUtils(){}

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

    public static String toTelefoneResidencialCelular(String numero){
        try {
            MaskFormatter telefoneCelularFormatacao = new MaskFormatter("(##) #####-####");
            telefoneCelularFormatacao.setValueContainsLiteralCharacters(false);
            return telefoneCelularFormatacao.valueToString(numero);
        } catch (Exception e){
            throw new MsgException("Erro formatação do telefone celular");
        }
    }
}
