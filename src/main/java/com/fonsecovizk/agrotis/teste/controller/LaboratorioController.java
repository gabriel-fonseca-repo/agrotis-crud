package com.fonsecovizk.agrotis.teste.controller;

import com.fonsecovizk.agrotis.teste.dto.ConsultarEstatisticasLaboratorioDTO;
import com.fonsecovizk.agrotis.teste.dto.LaboratorioDTO;
import com.fonsecovizk.agrotis.teste.dto.projections.EstatisticasLaboratorioDTO;
import com.fonsecovizk.agrotis.teste.service.LaboratorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaboratorioController {

  private final LaboratorioService laboratorioService;

  public LaboratorioController(LaboratorioService laboratorioService) {
    this.laboratorioService = laboratorioService;
  }

  // Create
  @PostMapping("/laboratorios")
  public ResponseEntity<?> criarLaboratorio(@RequestBody @Validated LaboratorioDTO laboratorioDTO) {
    laboratorioService.criarLaboratorio(laboratorioDTO);
    return ResponseEntity.ok().build();
  }

  // Read
  @GetMapping("/laboratorios/estatisticas")
  public ResponseEntity<List<EstatisticasLaboratorioDTO>> consultarEstatisticasLaboratorios(
    ConsultarEstatisticasLaboratorioDTO consultarEstatisticasLaboratorioDTO
  ) {
    List<EstatisticasLaboratorioDTO> estatisticasLaboratorioDTO = laboratorioService.consultarEstatisticasLaboratorios(
      consultarEstatisticasLaboratorioDTO
    );
    return ResponseEntity.ok(estatisticasLaboratorioDTO);
  }

  // Update
  @PutMapping("/laboratorios/{id}")
  public ResponseEntity<?> atualizarLaboratorio(
    @PathVariable Long id,
    @RequestBody @Validated LaboratorioDTO laboratorioDTO
  ) {
    laboratorioService.atualizarLaboratorio(id, laboratorioDTO);
    return ResponseEntity.ok().build();
  }

  // Delete
  @DeleteMapping("/laboratorios/{id}")
  public ResponseEntity<?> excluirLaboratorio(@PathVariable Long id) {
    laboratorioService.excluirLaboratorio(id);
    return ResponseEntity.ok().build();
  }

}
