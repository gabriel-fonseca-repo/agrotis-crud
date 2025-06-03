package com.fonsecovizk.agrotis.teste.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ConsultarEstatisticasLaboratorioDTO {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dataInicialStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dataInicialEnd;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dataFinalStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dataFinalEnd;

  private String observacoes;

  private Long quantidadeMinimaPessoas;

  public LocalDateTime getDataInicialStart() {
    return dataInicialStart;
  }

  public void setDataInicialStart(LocalDateTime dataInicialStart) {
    this.dataInicialStart = dataInicialStart;
  }

  public LocalDateTime getDataInicialEnd() {
    return dataInicialEnd;
  }

  public void setDataInicialEnd(LocalDateTime dataInicialEnd) {
    this.dataInicialEnd = dataInicialEnd;
  }

  public LocalDateTime getDataFinalStart() {
    return dataFinalStart;
  }

  public void setDataFinalStart(LocalDateTime dataFinalStart) {
    this.dataFinalStart = dataFinalStart;
  }

  public LocalDateTime getDataFinalEnd() {
    return dataFinalEnd;
  }

  public void setDataFinalEnd(LocalDateTime dataFinalEnd) {
    this.dataFinalEnd = dataFinalEnd;
  }

  public Long getQuantidadeMinimaPessoas() {
    return quantidadeMinimaPessoas;
  }

  public void setQuantidadeMinimaPessoas(Long quantidadeMinimaPessoas) {
    this.quantidadeMinimaPessoas = quantidadeMinimaPessoas;
  }

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }
}
