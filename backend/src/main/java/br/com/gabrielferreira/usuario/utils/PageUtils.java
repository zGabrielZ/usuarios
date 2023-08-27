package br.com.gabrielferreira.usuario.utils;

import br.com.gabrielferreira.usuario.exception.MsgException;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    private PageUtils(){}

    public static List<Sort.Order> getOrders(String[] sorts){
        List<Sort.Order> orders = new ArrayList<>();
        if (sorts != null && sorts.length != 0 && sorts[0].contains(",")) {
            for (String sort : sorts) {
                String[] dados = sort.split(",");
                Sort.Order order = new Sort.Order(verificarDirecao(dados.length != 2 ? null : dados[1]), dados[0]);
                orders.add(order);
            }
        } else if(sorts != null && sorts.length != 0 && !sorts[0].contains(",")){
            Sort.Order order = new Sort.Order(verificarDirecao(sorts.length != 2 ? null : sorts[1]), sorts[0]);
            orders.add(order);
        }
        return orders;
    }

    private static Sort.Direction verificarDirecao(String direcao){
        if(StringUtils.isBlank(direcao)){
            throw new MsgException("É necessário informar a direção (ASC ou DESC)");
        }
        return Sort.Direction.fromOptionalString(direcao)
                .orElseThrow(() -> new MsgException("A direção informada está incorreta, informe DESC ou ASC"));
    }
}
