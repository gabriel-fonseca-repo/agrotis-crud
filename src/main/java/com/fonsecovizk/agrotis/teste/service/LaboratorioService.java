package com.fonsecovizk.agrotis.teste.service;

import com.fonsecovizk.agrotis.teste.dto.ConsultarEstatisticasLaboratorioDTO;
import com.fonsecovizk.agrotis.teste.dto.LaboratorioDTO;
import com.fonsecovizk.agrotis.teste.dto.projections.EstatisticasLaboratorioDTO;
import com.fonsecovizk.agrotis.teste.entity.Laboratorio;
import com.fonsecovizk.agrotis.teste.repository.LaboratorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public List<EstatisticasLaboratorioDTO> consultarEstatisticasLaboratorios(
    ConsultarEstatisticasLaboratorioDTO consultarEstatisticasLaboratorioDTO
  ) {

    if (consultarEstatisticasLaboratorioDTO.getQuantidadeMinimaPessoas() == null
        || consultarEstatisticasLaboratorioDTO.getQuantidadeMinimaPessoas() < 0) {
      throw new RuntimeException("A quantidade mínima de pessoas é um campo obrigatório.");
    }

    return laboratorioRepository.consultarEstatisticasLaboratorios(
      consultarEstatisticasLaboratorioDTO.getDataInicialStart(),
      consultarEstatisticasLaboratorioDTO.getDataInicialEnd(),
      consultarEstatisticasLaboratorioDTO.getDataFinalStart(),
      consultarEstatisticasLaboratorioDTO.getDataFinalEnd(),
      consultarEstatisticasLaboratorioDTO.getObservacoes(),
      consultarEstatisticasLaboratorioDTO.getQuantidadeMinimaPessoas()
    );
  }

  public void excluirLaboratorio(Long id) {
    Laboratorio laboratorio = consultarPorId(id);

    if (laboratorio == null) {
      throw new RuntimeException("Laboratório não encontrado com o ID: " + id);
    }

    if (laboratorioRepository.existePessoaNoLaboratorio(id) == 1) {
      throw new RuntimeException("Não é possível excluir o laboratório com ID " + id + " porque existem pessoas associadas a ele.");
    }
    laboratorioRepository.delete(laboratorio);
    laboratorioRepository.flush();
  }

  public Laboratorio atualizarLaboratorio(Long id, LaboratorioDTO laboratorioDTO) {
    Laboratorio laboratorio = consultarPorId(id);
    if (laboratorio == null) {
      throw new RuntimeException("Laboratório não encontrado com o ID: " + id);
    }
    if (laboratorioDTO.getNome() == null || laboratorioDTO.getNome().isBlank()) {
      throw new RuntimeException("O nome do laboratório não pode ser vazio.");
    }
    laboratorio.setNome(laboratorioDTO.getNome());
    return laboratorioRepository.saveAndFlush(laboratorio);
  }

  public Laboratorio criarLaboratorio(LaboratorioDTO laboratorioDTO) {
    if (laboratorioDTO.getNome() == null || laboratorioDTO.getNome().isBlank()) {
      throw new RuntimeException("O nome do laboratório não pode ser vazio.");
    }
    Laboratorio laboratorio = new Laboratorio();
    laboratorio.setNome(laboratorioDTO.getNome());
    return laboratorioRepository.saveAndFlush(laboratorio);
  }
}
