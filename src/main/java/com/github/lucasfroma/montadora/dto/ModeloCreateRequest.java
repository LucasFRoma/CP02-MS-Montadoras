package com.github.lucasfroma.montadora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ModeloCreateRequest {

    @NotBlank
    @Size(min = 2, message = "Nome do modelo deve ter no mínimo 2 caracteres")
    private String nome;

    @NotBlank(message = "Franquia/ano é obrigatório")
    private String franquia;

    @NotBlank(message = "Classificação é obrigatória")
    private String classificacao;

    @NotBlank(message = "Fabricante é obrigatório")
    private String fabricante;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFranquia() {
        return franquia;
    }

    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

}
