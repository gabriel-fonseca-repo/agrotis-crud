package com.fonsecovizk.agrotis.teste.service;

import com.fonsecovizk.agrotis.teste.dto.ConsultarPessoasDTO;
import com.fonsecovizk.agrotis.teste.dto.CriarPessoaDTO;
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

  public void criarPessoa(CriarPessoaDTO pessoa) {

    Pessoa novaPessoa = new Pessoa();
    novaPessoa.setNome(pessoa.getNome());
    novaPessoa.setDataInicial(pessoa.getDataInicial());
    novaPessoa.setDataFinal(pessoa.getDataFinal());

    Laboratorio laboratorio = laboratorioService.consultarPorId(pessoa.getLaboratorio().getId());
    Propriedade propriedade = propriedadeService.consultarPorId(pessoa.getInfosPropriedade().getId());
    novaPessoa.setLaboratorio(laboratorio);
    novaPessoa.setPropriedade(propriedade);

    novaPessoa.setObservacoes(pessoa.getObservacoes());

    pessoaRepository.save(novaPessoa);
    pessoaRepository.flush();

  }
}
