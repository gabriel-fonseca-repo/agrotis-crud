package com.fonsecovizk.agrotis.teste.service;

import com.fonsecovizk.agrotis.teste.entity.Laboratorio;
import com.fonsecovizk.agrotis.teste.repository.LaboratorioRepository;
import org.springframework.stereotype.Service;

@Service
public class LaboratorioService {

  private final LaboratorioRepository laboratorioRepository;

  public LaboratorioService(LaboratorioRepository laboratorioRepository) {
    this.laboratorioRepository = laboratorioRepository;
  }

  public Laboratorio consultarPorId(Long id) {
    return laboratorioRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Laboratorio não encontrado com o ID: " + id));
  }
}
