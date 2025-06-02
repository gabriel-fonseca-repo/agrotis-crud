package com.fonsecovizk.agrotis.teste.service;

import com.fonsecovizk.agrotis.teste.entity.Pessoa;
import com.fonsecovizk.agrotis.teste.entity.Propriedade;
import com.fonsecovizk.agrotis.teste.repository.PropriedadeRepository;
import org.springframework.stereotype.Service;

@Service
public class PropriedadeService {

  private final PropriedadeRepository propriedadeRepository;

  public PropriedadeService(PropriedadeRepository propriedadeRepository) {
    this.propriedadeRepository = propriedadeRepository;
  }

  public Propriedade consultarPorId(Long id) {
    return propriedadeRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Propriedade não encontrada com o ID: " + id));
  }
}
