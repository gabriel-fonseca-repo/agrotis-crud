package com.fonsecovizk.agrotis.teste;

import com.fonsecovizk.agrotis.teste.controller.LaboratorioController;
import com.fonsecovizk.agrotis.teste.dto.ConsultarEstatisticasLaboratorioDTO;
import com.fonsecovizk.agrotis.teste.dto.projections.EstatisticasLaboratorioDTO;
import com.fonsecovizk.agrotis.teste.service.LaboratorioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ConsultarEstatisticasLaboratorioTest {

  @Mock
  private LaboratorioService laboratorioService;

  @InjectMocks
  private LaboratorioController laboratorioController;

  @Test
  void consultarEstatisticasLaboratorios_shouldReturnStatus200() {
    ConsultarEstatisticasLaboratorioDTO consultarEstatisticasLaboratorioDTO = getConsultarEstatisticasLaboratorioDTO();
    ResponseEntity<List<EstatisticasLaboratorioDTO>> response = laboratorioController
      .consultarEstatisticasLaboratorios(consultarEstatisticasLaboratorioDTO);

    response.getBody().forEach(estatisticas -> {
      assertTrue(
        estatisticas.getQuantidadePessoas() >= consultarEstatisticasLaboratorioDTO.getQuantidadeMinimaPessoas(),
        "A quantidade de pessoas deve ser maior ou igual à quantidade mínima especificada."
      );
    });

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  private static ConsultarEstatisticasLaboratorioDTO getConsultarEstatisticasLaboratorioDTO() {
    LocalDateTime dataInicialStart = LocalDateTime.now().minusDays(120);
    LocalDateTime dataInicialEnd = LocalDateTime.now();
    LocalDateTime dataFinalStart = LocalDateTime.now().minusDays(120);
    LocalDateTime dataFinalEnd = LocalDateTime.now();
    String observacoes = "ipsa";
    Long quantidadeMinimaPessoas = 15L;

    ConsultarEstatisticasLaboratorioDTO consultarEstatisticasLaboratorioDTO = new ConsultarEstatisticasLaboratorioDTO();
    consultarEstatisticasLaboratorioDTO.setDataInicialStart(dataInicialStart);
    consultarEstatisticasLaboratorioDTO.setDataInicialEnd(dataInicialEnd);
    consultarEstatisticasLaboratorioDTO.setDataFinalStart(dataFinalStart);
    consultarEstatisticasLaboratorioDTO.setDataFinalEnd(dataFinalEnd);
    consultarEstatisticasLaboratorioDTO.setObservacoes(observacoes);
    consultarEstatisticasLaboratorioDTO.setQuantidadeMinimaPessoas(quantidadeMinimaPessoas);
    return consultarEstatisticasLaboratorioDTO;
  }

}
