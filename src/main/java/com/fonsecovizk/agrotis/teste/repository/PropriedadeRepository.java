package com.fonsecovizk.agrotis.teste.repository;

import com.fonsecovizk.agrotis.teste.entity.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long>, JpaSpecificationExecutor<Propriedade> {

  @Query(value = """
    SELECT EXISTS (
        SELECT 1
        FROM pessoa
        WHERE id_propriedade = :idPropriedade
        LIMIT 1
    )
    """, nativeQuery = true)
  Integer existePessoaNaPropriedade(@Param("idPropriedade") Long idPropriedade);

}
