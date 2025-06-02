package com.fonsecovizk.agrotis.teste.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private LocalDateTime dataInicial;

  @Column(nullable = false)
  private LocalDateTime dataFinal;

  @ManyToOne
  @JoinColumn(name = "id_propriedade", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_propriedade"))
  private Propriedade propriedade;

  @ManyToOne
  @JoinColumn(name = "id_laboratorio", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_laboratorio"))
  private Laboratorio laboratorio;

  @Column
  private String observacoes;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
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

  public Propriedade getPropriedade() {
    return propriedade;
  }

  public void setPropriedade(Propriedade propriedade) {
    this.propriedade = propriedade;
  }

  public Laboratorio getLaboratorio() {
    return laboratorio;
  }

  public void setLaboratorio(Laboratorio laboratorio) {
    this.laboratorio = laboratorio;
  }

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }
}
