package com.fonsecovizk.agrotis.teste.repository;

import com.fonsecovizk.agrotis.teste.dto.projections.EstatisticasLaboratorioDTO;
import com.fonsecovizk.agrotis.teste.entity.Laboratorio;
import com.fonsecovizk.agrotis.teste.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>, JpaSpecificationExecutor<Laboratorio> {

  @Query(value = """
    SELECT
        l.id AS idLaboratorio,
        l.nome AS nomeLaboratorio,
        COUNT(p.id) AS quantidadePessoas
    FROM laboratorio l
    JOIN pessoa p ON p.id_laboratorio = l.id
    WHERE (:dataInicialStart IS NULL OR p.data_inicial >= :dataInicialStart)
      AND (:dataInicialEnd IS NULL OR p.data_inicial <= :dataInicialEnd)
      AND (:dataFinalStart IS NULL OR p.data_final >= :dataFinalStart)
      AND (:dataFinalEnd IS NULL OR p.data_final <= :dataFinalEnd)
      AND (:observacoes IS NULL OR p.observacoes LIKE CONCAT('%', :observacoes, '%'))
    GROUP BY l.id, l.nome
    HAVING (:quantidadeMinima IS NULL OR COUNT(p.id) >= :quantidadeMinima)
    ORDER BY quantidadePessoas DESC
    """, nativeQuery = true)
  List<EstatisticasLaboratorioDTO> consultarEstatisticasLaboratorios(
    @Param("dataInicialStart") LocalDateTime dataInicialStart,
    @Param("dataInicialEnd") LocalDateTime dataInicialEnd,
    @Param("dataFinalStart") LocalDateTime dataFinalStart,
    @Param("dataFinalEnd") LocalDateTime dataFinalEnd,
    @Param("observacoes") String observacoes,
    @Param("quantidadeMinima") Long quantidadeMinima
  );

  @Query(value = """
        SELECT EXISTS (
            SELECT 1
            FROM pessoa
            WHERE id_laboratorio = :idLaboratorio
            LIMIT 1
        )
        """, nativeQuery = true)
  Integer existePessoaNoLaboratorio(@Param("idLaboratorio") Long idLaboratorio);

}
