package com.fonsecovizk.agrotis.teste.dto;

import java.time.LocalDateTime;

public class ConsultarPessoasDTO {

  private Long id;

  private String nome;

  private LocalDateTime dataInicial;

  private LocalDateTime dataFinal;

  private Long idPropriedade;

  private Long idLaboratorio;

  private String observacoes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDateTime getDataInicial() {
    return dataInicial;
  }

  public void setDataInicial(LocalDateTime dataInicial) {
    this.dataInicial = dataInicial;
  }

  public LocalDateTime getDataFinal() {
    return dataFinal;
  }

  public void setDataFinal(LocalDateTime dataFinal) {
    this.dataFinal = dataFinal;
  }

  public Long getIdPropriedade() {
    return idPropriedade;
  }

  public void setIdPropriedade(Long idPropriedade) {
    this.idPropriedade = idPropriedade;
  }

  public Long getIdLaboratorio() {
    return idLaboratorio;
  }

  public void setIdLaboratorio(Long idLaboratorio) {
    this.idLaboratorio = idLaboratorio;
  }

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }
}
