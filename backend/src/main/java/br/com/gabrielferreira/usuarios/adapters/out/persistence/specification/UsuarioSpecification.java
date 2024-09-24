package br.com.gabrielferreira.usuarios.adapters.out.persistence.specification;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UsuarioSpecification implements Specification<UsuarioEntity> {

    private final String nome;

    private final String email;

    private final BigDecimal renda;

    @Override
    public Predicate toPredicate(Root<UsuarioEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.isNotBlank(nome)){
            Predicate predicateNome = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
            predicates.add(predicateNome);
        }

        if(StringUtils.isNotBlank(email)){
            Predicate predicateEmail = criteriaBuilder.equal(root.get("email"), email);
            predicates.add(predicateEmail);
        }

        if(renda != null){
            Predicate predicateRenda = criteriaBuilder.greaterThanOrEqualTo(root.get("renda"), renda);
            predicates.add(predicateRenda);
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
