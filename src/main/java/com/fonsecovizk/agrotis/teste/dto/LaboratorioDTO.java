package com.fonsecovizk.agrotis.teste.dto;

import jakarta.validation.constraints.NotBlank;

public class LaboratorioDTO {

  private Long id;

  @NotBlank(message = "O nome não pode ser vazio")
  private String nome;

  public LaboratorioDTO(Long id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public LaboratorioDTO(String nome) {
    this.nome = nome;
  }

  public LaboratorioDTO() {
  }

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

}
