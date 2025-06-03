package com.fonsecovizk.agrotis.teste.controller;

import com.fonsecovizk.agrotis.teste.dto.ConsultarPessoasDTO;
import com.fonsecovizk.agrotis.teste.dto.PessoaDTO;
import com.fonsecovizk.agrotis.teste.entity.Pessoa;
import com.fonsecovizk.agrotis.teste.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PessoaController {

  private final PessoaService pessoaService;

  public PessoaController(PessoaService pessoaService) {
    this.pessoaService = pessoaService;
  }

  // Create
  @PostMapping("/pessoas")
  public ResponseEntity<?> criarPessoa(@RequestBody @Validated PessoaDTO pessoa) {
    pessoaService.criarPessoa(pessoa);
    return ResponseEntity.ok().build();
  }

  // Read
  @GetMapping("/pessoas")
  public ResponseEntity<List<Pessoa>> listarPessoas(ConsultarPessoasDTO consultarPessoasDTO) {
    List<Pessoa> pessoas = pessoaService.listarTodasComFiltro(consultarPessoasDTO);
    return ResponseEntity.ok(pessoas);
  }

  // Update
  @PutMapping("/pessoas/{id}")
  public ResponseEntity<?> atualizarPessoa(@PathVariable Long id, @RequestBody @Validated PessoaDTO pessoa) {
    pessoaService.atualizarPessoa(id, pessoa);
    return ResponseEntity.ok().build();
  }

  // Delete
  @DeleteMapping("/pessoas/{id}")
  public ResponseEntity<?> deletarPessoa(@PathVariable Long id) {
    pessoaService.deletarPessoa(id);
    return ResponseEntity.ok().build();
  }

}
