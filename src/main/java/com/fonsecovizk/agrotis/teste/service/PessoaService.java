package com.fonsecovizk.agrotis.teste.service;

import com.fonsecovizk.agrotis.teste.dto.ConsultarPessoasDTO;
import com.fonsecovizk.agrotis.teste.dto.PessoaDTO;
import com.fonsecovizk.agrotis.teste.entity.Laboratorio;
import com.fonsecovizk.agrotis.teste.entity.Pessoa;
import com.fonsecovizk.agrotis.teste.entity.Propriedade;
import com.fonsecovizk.agrotis.teste.repository.PessoaRepository;
import com.fonsecovizk.agrotis.teste.specification.PessoaSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

  private final PessoaRepository pessoaRepository;

  private final LaboratorioService laboratorioService;

  private final PropriedadeService propriedadeService;

  public PessoaService(PessoaRepository pessoaRepository,
                       LaboratorioService laboratorioService,
                       PropriedadeService propriedadeService) {
    this.pessoaRepository = pessoaRepository;
    this.laboratorioService = laboratorioService;
    this.propriedadeService = propriedadeService;
  }

  public List<Pessoa> listarTodasComFiltro(ConsultarPessoasDTO consultarPessoasDTO) {
    return pessoaRepository.findAll(PessoaSpecification.consultarPessoasComFiltro(consultarPessoasDTO));
  }

  public Pessoa criarPessoa(PessoaDTO pessoa) {

    Pessoa novaPessoa = new Pessoa();
    novaPessoa.setNome(pessoa.getNome());
    novaPessoa.setDataInicial(pessoa.getDataInicial());
    novaPessoa.setDataFinal(pessoa.getDataFinal());

    Laboratorio laboratorio = laboratorioService.consultarPorId(pessoa.getLaboratorio().getId());
    Propriedade propriedade = propriedadeService.consultarPorId(pessoa.getInfosPropriedade().getId());
    novaPessoa.setLaboratorio(laboratorio);
    novaPessoa.setPropriedade(propriedade);

    novaPessoa.setObservacoes(pessoa.getObservacoes());

    return pessoaRepository.saveAndFlush(novaPessoa);
  }

  public void atualizarPessoa(Long id, PessoaDTO pessoa) {
    Pessoa pessoaExistente = pessoaRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + id));

    if (pessoa.getNome() != null && !pessoa.getNome().isEmpty()) {
      pessoaExistente.setNome(pessoa.getNome());
    }

    if (pessoa.getDataInicial() != null)
      pessoaExistente.setDataInicial(pessoa.getDataInicial());
    if (pessoa.getDataFinal() != null)
      pessoaExistente.setDataFinal(pessoa.getDataFinal());

    if (pessoa.getLaboratorio() != null && pessoa.getLaboratorio().getId() != null) {
      Laboratorio laboratorio = laboratorioService.consultarPorId(pessoa.getLaboratorio().getId());
      pessoaExistente.setLaboratorio(laboratorio);
    }

    if (pessoa.getInfosPropriedade() != null && pessoa.getInfosPropriedade().getId() != null) {
      Propriedade propriedade = propriedadeService.consultarPorId(pessoa.getInfosPropriedade().getId());
      pessoaExistente.setPropriedade(propriedade);
    }

    if (pessoa.getObservacoes() != null && !pessoa.getObservacoes().isEmpty()) {
      pessoaExistente.setObservacoes(pessoa.getObservacoes());
    }

    pessoaRepository.saveAndFlush(pessoaExistente);
  }

  public void deletarPessoa(Long id) {
    Pessoa pessoaExistente = pessoaRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + id));

    pessoaRepository.delete(pessoaExistente);
    pessoaRepository.flush();
  }
}
