package com.github.lucasfroma.montadora.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empresas")
public class Montadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "ramo", nullable = false)
    private String ramo;

    @Column(name = "sede", nullable = false)
    private String sede;
}
