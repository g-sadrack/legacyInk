package br.com.legacyink.infraestructure.spec;

import br.com.legacyink.domain.model.Agendamento;
import br.com.legacyink.domain.repository.filter.AgendamentoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class AgendamentoSpecs {

    public static Specification<Agendamento> usandoFiltro(AgendamentoFilter filtro) {

        return ((root, query, criteriaBuilder) -> {
            root.fetch("cliente").fetch("endereco");

            var predicates = new ArrayList<Predicate>();

            if (filtro.getCodigo() != null) {
                predicates.add(criteriaBuilder.equal(root.get("codigo"), filtro.getCodigo()));
            }
            if (filtro.getClienteId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("cliente"), filtro.getClienteId()));
            }
            if (filtro.getDataCadastro() != null) {
                predicates.add(criteriaBuilder.equal(root.get("dataCadastro"), filtro.getDataCadastro()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

    }

}
