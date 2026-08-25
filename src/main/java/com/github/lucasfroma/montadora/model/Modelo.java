package com.github.lucasfroma.montadora.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jogos")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "ano", nullable = false)
    private String franquia;

    @Column(name = "classificacao", nullable = false)
    private String classificacao;

    @Column(name = "fabricante", nullable = false)
    private String fabricante;

}
