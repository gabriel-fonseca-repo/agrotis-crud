package com.fonsecovizk.agrotis.teste.service;

import com.fonsecovizk.agrotis.teste.dto.PropriedadeDTO;
import com.fonsecovizk.agrotis.teste.entity.Pessoa;
import com.fonsecovizk.agrotis.teste.entity.Propriedade;
import com.fonsecovizk.agrotis.teste.repository.PropriedadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public Propriedade atualizarPropriedade(Long id, PropriedadeDTO propriedadeDTO) {
    if (id == null) {
      throw new RuntimeException("O ID da propriedade não pode ser nulo.");
    }
    Propriedade propriedade = consultarPorId(id);

    if (propriedade == null) {
      throw new RuntimeException("Propriedade não encontrada com o ID: " + id);
    }

    if (propriedadeDTO.getNome() == null || propriedadeDTO.getNome().isBlank()) {
      throw new RuntimeException("O nome da propriedade não pode ser vazio.");
    }

    propriedade.setNome(propriedadeDTO.getNome());
    return propriedadeRepository.saveAndFlush(propriedade);
  }

  public Propriedade criarPropriedade(PropriedadeDTO propriedadeDTO) {
    if (propriedadeDTO.getNome() == null || propriedadeDTO.getNome().isBlank()) {
      throw new RuntimeException("O nome da propriedade não pode ser vazio.");
    }
    Propriedade propriedade = new Propriedade();
    propriedade.setNome(propriedadeDTO.getNome());
    return propriedadeRepository.saveAndFlush(propriedade);
  }

  public List<PropriedadeDTO> consultarPropriedadeDTO() {
    List<Propriedade> propriedades = propriedadeRepository.findAll();
    return propriedades.stream()
      .map(propriedade -> new PropriedadeDTO(propriedade.getId(), propriedade.getNome()))
      .toList();
  }

  public void excluirPropriedade(Long id) {
    Propriedade propriedade = consultarPorId(id);

    if (propriedade == null) {
      throw new RuntimeException("Propriedade não encontrada com o ID: " + id);
    }

    if (propriedadeRepository.existePessoaNaPropriedade(id) == 1) {
      throw new RuntimeException("Não é possível excluir a propriedade com ID " + id + " porque existem pessoas associadas a ela.");
    }

    propriedadeRepository.delete(propriedade);
    propriedadeRepository.flush();
  }
}
