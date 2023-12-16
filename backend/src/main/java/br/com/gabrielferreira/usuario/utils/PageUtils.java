package br.com.gabrielferreira.usuario.utils;

import br.com.gabrielferreira.usuario.exception.MsgException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageUtils {

    private PageUtils(){}

    public static void validarPropriedades(Sort sorts, List<String> propriedades){
        for (Sort.Order sort : sorts) {
            String propriedade = sort.getProperty();
            if(!propriedades.contains(propriedade)){
                throw new MsgException(String.format("A propriedade informada '%s' não existe", propriedade));
            }
        }
    }

    public static List<String> propriedadesAnotacao(){
        return Arrays.asList("id", "titulo", "descricao", "tipoAnotacao.id", "tipoAnotacao.descricao", "tipoAnotacao.codigo", "tipoAnotacao.tipo.id", "tipoAnotacao.tipo.descricao",
                "tipoAnotacao.tipo.codigo", "dataLembrete", "dataEstudoInicio", "dataEstudoFim", "situacaoTipoAnotacao.id", "situacaoTipoAnotacao.descricao", "situacaoTipoAnotacao.codigo",
                "situacaoTipoAnotacao.tipo.id", "situacaoTipoAnotacao.tipo.descricao", "situacaoTipoAnotacao.tipo.codigo","createdAt", "updatedAt");
    }

    public static List<String> propriedadesUsuario(){
        return Arrays.asList("id", "nome", "email", "cpf", "cpfFormatado", "renda", "rendaFormatada", "dataNascimento", "quantidadeFilhos",
                "telefone.id", "telefone.numero", "telefone.ddd", "telefone.telefoneFormatado", "telefone.descricao", "telefone.tipoTelefone.id" ,
                "telefone.tipoTelefone.descricao", "telefone.tipoTelefone.codigo", "telefone.tipoTelefone.tipo.id", "telefone.tipoTelefone.tipo.descricao",
                "telefone.tipoTelefone.tipo.codigo", "genero.id", "genero.descricao", "genero.codigo", "genero.tipo.id", "genero.tipo.descricao", "genero.tipo.codigo",
                "createdAt", "updatedAt");
    }

    public static Pageable validarOrderBy(Pageable pageable, Map<String, String> atributoDtoToEntity){
        List<Sort.Order> sorts = pageable.getSort().stream().collect(Collectors.toList());
        if(!sorts.isEmpty() && !atributoDtoToEntity.isEmpty()){
            boolean isPropriedadeEncontrada = false;
            for (Sort.Order sort : sorts) {
                String propriedadeEncontrada = atributoDtoToEntity.get(sort.getProperty());
                if(propriedadeEncontrada != null){
                    isPropriedadeEncontrada = true;
                    Sort.Order novoSort = new Sort.Order(sort.getDirection(), propriedadeEncontrada);
                    sorts.set(sorts.indexOf(sort), novoSort);
                }
            }

            if(isPropriedadeEncontrada){
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sorts));
            }
        }

        return pageable;
    }
}
