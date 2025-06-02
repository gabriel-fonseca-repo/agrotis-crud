package com.fonsecovizk.agrotis.teste.specification;

import com.fonsecovizk.agrotis.teste.dto.ConsultarPessoasDTO;
import com.fonsecovizk.agrotis.teste.entity.Laboratorio;
import com.fonsecovizk.agrotis.teste.entity.Pessoa;
import com.fonsecovizk.agrotis.teste.entity.Propriedade;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PessoaSpecification {

  public static Specification<Pessoa> consultarPessoasComFiltro(ConsultarPessoasDTO filtro) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (filtro != null) {
        if (filtro.getId() != null) {
          predicates.add(cb.equal(root.get("id"), filtro.getId()));
        }

        if (filtro.getNome() != null) {
          predicates.add(cb.like(root.get("nome"), "%" + filtro.getNome() + "%"));
        }

        if (filtro.getDataInicial() != null) {
          predicates.add(cb.equal(root.get("dataInicial"), filtro.getDataInicial()));
        }

        if (filtro.getDataFinal() != null) {
          predicates.add(cb.equal(root.get("dataFinal"), filtro.getDataFinal()));
        }

        if (filtro.getIdLaboratorio() != null) {
          Join<Pessoa, Laboratorio> join = root.join("laboratorio", JoinType.INNER);
          predicates.add(cb.equal(join.get("id"), filtro.getIdLaboratorio()));
        }

        if (filtro.getIdPropriedade() != null) {
          Join<Pessoa, Propriedade> join = root.join("propriedade", JoinType.INNER);
          predicates.add(cb.equal(join.get("id"), filtro.getIdPropriedade()));
        }

        if (filtro.getObservacoes() != null) {
          predicates.add(cb.like(root.get("observacoes"), "%" + filtro.getObservacoes() + "%"));
        }
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

}
