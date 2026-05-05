package com.github.lucasfroma.montadora.repository;

import com.github.lucasfroma.montadora.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}
