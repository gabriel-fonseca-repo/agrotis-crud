package com.fonsecovizk.agrotis.teste;

import com.fonsecovizk.agrotis.teste.controller.PessoaController;
import com.fonsecovizk.agrotis.teste.dto.LaboratorioDTO;
import com.fonsecovizk.agrotis.teste.dto.PessoaDTO;
import com.fonsecovizk.agrotis.teste.dto.PropriedadeDTO;
import com.fonsecovizk.agrotis.teste.entity.Laboratorio;
import com.fonsecovizk.agrotis.teste.entity.Propriedade;
import com.fonsecovizk.agrotis.teste.service.LaboratorioService;
import com.fonsecovizk.agrotis.teste.service.PessoaService;
import com.fonsecovizk.agrotis.teste.service.PropriedadeService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CadastrarPessoaTest {

  @Mock
  private LaboratorioService laboratorioService;

  @Mock
  private PropriedadeService propriedadeService;

  @Mock
  private PessoaService pessoaService;

  @InjectMocks
  private PessoaController pessoaController;

  @Test
  void cadastrarPessoa_shouldReturnStatus200() {
    PessoaDTO pessoaFalsa = criarPessoaFalsaTeste();
    ResponseEntity<?> response = pessoaController.criarPessoa(pessoaFalsa);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  private PessoaDTO criarPessoaFalsaTeste() {
    Faker faker = new Faker();
    Random random = new Random();

    Laboratorio laboratorio = laboratorioService.criarLaboratorio(new LaboratorioDTO("Laboratório " + faker.company().name()));
    Propriedade propriedade = propriedadeService.criarPropriedade(new PropriedadeDTO("Propriedade " + faker.address().streetName()));

    PessoaDTO novaPessoa = new PessoaDTO();
    novaPessoa.setNome(faker.name().fullName());
    novaPessoa.setDataInicial(faker.date().past(365, TimeUnit.DAYS).toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime());
    novaPessoa.setDataFinal(faker.date().future(365, TimeUnit.DAYS).toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime());

    LaboratorioDTO laboratorioDto = new LaboratorioDTO();
    PropriedadeDTO propriedadeDto = new PropriedadeDTO();

    laboratorioDto.setId(laboratorio.getId());
    propriedadeDto.setId(propriedade.getId());

    novaPessoa.setLaboratorio(laboratorioDto);
    novaPessoa.setInfosPropriedade(propriedadeDto);

    novaPessoa.setObservacoes(faker.lorem().sentence());

    return novaPessoa;
  }

}
