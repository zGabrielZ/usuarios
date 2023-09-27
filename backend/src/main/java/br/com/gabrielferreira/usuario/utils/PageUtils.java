package br.com.gabrielferreira.usuario.utils;

import br.com.gabrielferreira.usuario.exception.MsgException;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    private PageUtils(){}

    public static void validarPropriedades(Sort sorts, Class<?> clazz){
        if(!sorts.isEmpty()){
            List<String> propriedadesInformadas = sorts.stream().map(s -> s.getProperty().toLowerCase()).toList();
            List<String> campos = listarAtributosRecursivamente(new ArrayList<>(), "", clazz);

            propriedadesInformadas.forEach(propriedadeInformada -> {
                if(!campos.contains(propriedadeInformada)){
                    throw new MsgException(String.format("A propriedade informada %s não existe", propriedadeInformada));
                }
            });
        }
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
