package br.com.gabrielferreira.usuarios.adapters.out.persistence.specification;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AnotacaoSpecification implements Specification<AnotacaoEntity> {

    private final String titulo;

    private final String descricao;

    private final Long idUsuario;

    @Override
    public Predicate toPredicate(Root<AnotacaoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Join<AnotacaoEntity, UsuarioEntity> usuarioEntityJoin = root.join("usuario");
        predicates.add(criteriaBuilder.equal(usuarioEntityJoin.get("id"), idUsuario));

        if(StringUtils.isNotBlank(titulo)){
            Predicate predicateTitulo = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
            predicates.add(predicateTitulo);
        }

        if(StringUtils.isNotBlank(descricao)){
            Predicate predicateDescricao = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
            predicates.add(predicateDescricao);
        }

        if (currentQueryIsCountRecords(query)) {
            root.join("tipoAnotacao", JoinType.INNER)
                    .join("tipo", JoinType.INNER);
            root.join("situacaoTipoAnotacao", JoinType.INNER)
                    .join("tipo", JoinType.INNER);
        } else {
            root.fetch("tipoAnotacao", JoinType.INNER)
                    .fetch("tipo", JoinType.INNER);
            root.fetch("situacaoTipoAnotacao", JoinType.INNER)
                    .fetch("tipo", JoinType.INNER);
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    private boolean currentQueryIsCountRecords(CriteriaQuery<?> criteriaQuery) {
        return criteriaQuery.getResultType() == Long.class || criteriaQuery.getResultType() == long.class;
    }
}
