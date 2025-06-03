package com.fonsecovizk.agrotis.teste;

import com.fonsecovizk.agrotis.teste.entity.Laboratorio;
import com.fonsecovizk.agrotis.teste.entity.Pessoa;
import com.fonsecovizk.agrotis.teste.entity.Propriedade;
import com.fonsecovizk.agrotis.teste.repository.LaboratorioRepository;
import com.fonsecovizk.agrotis.teste.repository.PessoaRepository;
import com.fonsecovizk.agrotis.teste.repository.PropriedadeRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TesteAgrotisApplication {

  public static void main(String[] args) {
    SpringApplication.run(TesteAgrotisApplication.class, args);
  }

  /**
   * Método para inicializar o banco de dados com alguns dados de exemplo.
   * Desconsiderar para fins da avaliação, ele claramente seria removido no mundo real.
   */
  @Bean
  CommandLineRunner init(
    PessoaRepository pessoaRepository,
    LaboratorioRepository laboratorioRepository,
    PropriedadeRepository propriedadeRepository
  ) {
    return args -> {
      if (pessoaRepository.count() == 0) {

        Faker faker = new Faker();
        Random random = new Random();

        laboratorioRepository.saveAllAndFlush(
          List.of(
            new Laboratorio("Laboratório " + faker.company().name()),
            new Laboratorio("Laboratório " + faker.company().name()),
            new Laboratorio("Laboratório " + faker.company().name()),
            new Laboratorio("Laboratório " + faker.company().name()),
            new Laboratorio("Laboratório " + faker.company().name())
          )
        );

        propriedadeRepository.saveAllAndFlush(
          List.of(
            new Propriedade("Propriedade " + faker.address().streetName()),
            new Propriedade("Propriedade " + faker.address().streetName()),
            new Propriedade("Propriedade " + faker.address().streetName()),
            new Propriedade("Propriedade " + faker.address().streetName()),
            new Propriedade("Propriedade " + faker.address().streetName())
          )
        );

        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        List<Propriedade> propriedades = propriedadeRepository.findAll();

        List<Pessoa> pessoas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
          Pessoa novaPessoa = new Pessoa();
          novaPessoa.setNome(faker.name().fullName());
          novaPessoa.setDataInicial(faker.date().past(365, TimeUnit.DAYS).toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime());
          novaPessoa.setDataFinal(faker.date().future(365, TimeUnit.DAYS).toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime());

          novaPessoa.setLaboratorio(laboratorios.get(random.nextInt(laboratorios.size())));
          novaPessoa.setPropriedade(propriedades.get(random.nextInt(propriedades.size())));

          novaPessoa.setObservacoes(faker.lorem().sentence());

          pessoas.add(novaPessoa);
        }

        pessoaRepository.saveAllAndFlush(pessoas);
      }
    };
  }

}
