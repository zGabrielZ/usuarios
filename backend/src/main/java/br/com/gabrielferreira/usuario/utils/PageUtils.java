package br.com.gabrielferreira.usuario.utils;

import br.com.gabrielferreira.usuario.exception.MsgException;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    private PageUtils(){}

    public static void validarPropriedades(Sort sorts, Class<?> clazz){
        List<String> campos = listarAtributosRecursivamente(new ArrayList<>(), "", clazz);
        List<String> propriedaes = sorts.stream().map(Sort.Order::getProperty).toList();

        propriedaes.forEach(propriedade -> {
            if(!campos.contains(propriedade)){
                throw new MsgException(String.format("A propriedade informada %s não existe", propriedade));
            }
        });
    }

    private static List<String> listarAtributosRecursivamente(List<String> campos, String prefixo, Class<?> classe) {
        for (Field campo : classe.getDeclaredFields()) {
            String nomeCampo = prefixo.concat(campo.getName());
            campos.add(nomeCampo);

            if(!campo.getType().isPrimitive() && !campo.getType().getName().startsWith("java.")){
                campos.remove(nomeCampo);
                listarAtributosRecursivamente(campos, nomeCampo.concat("."), campo.getType());
            }
        }
        return campos;
    }
}
