package com.github.lucasfroma.montadora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MontadoraCreateRequest {

    @NotBlank
    @Size(min = 2, message = "Nome da montadora deve ter no mínimo 2 caracteres")
    private String nome;

    @NotBlank(message = "País é obrigatório")
    private String pais;

    @NotBlank(message = "Ramo é obrigatório")
    private String ramo;

    @NotBlank(message = "Sede é obrigatória")
    private String sede;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

}
