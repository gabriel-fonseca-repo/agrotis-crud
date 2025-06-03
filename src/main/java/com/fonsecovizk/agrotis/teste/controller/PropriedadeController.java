package com.fonsecovizk.agrotis.teste.controller;

import com.fonsecovizk.agrotis.teste.dto.PropriedadeDTO;
import com.fonsecovizk.agrotis.teste.service.PropriedadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropriedadeController {

  private final PropriedadeService propriedadeService;

  public PropriedadeController(PropriedadeService propriedadeService) {
    this.propriedadeService = propriedadeService;
  }

  // Create
  @PostMapping("/propriedades")
  public ResponseEntity<?> criarPropriedade(@RequestBody @Validated PropriedadeDTO propriedadeDTO) {
    propriedadeService.criarPropriedade(propriedadeDTO);
    return ResponseEntity.ok().build();
  }

  // Read
  @GetMapping("/propriedades")
  public ResponseEntity<List<PropriedadeDTO>> consultarPropriedades() {
    List<PropriedadeDTO> propriedadesDTO = propriedadeService.consultarPropriedadeDTO();
    return ResponseEntity.ok(propriedadesDTO);
  }

  // Update
  @PutMapping("/propriedades/{id}")
  public ResponseEntity<?> atualizarPropriedade(
    @PathVariable Long id,
    @RequestBody @Validated PropriedadeDTO propriedadeDTO
  ) {
    propriedadeService.atualizarPropriedade(id, propriedadeDTO);
    return ResponseEntity.ok().build();
  }

  // Delete
  @DeleteMapping("/propriedades/{id}")
  public ResponseEntity<?> excluirPropriedade(@PathVariable Long id) {
    propriedadeService.excluirPropriedade(id);
    return ResponseEntity.ok().build();
  }

}
