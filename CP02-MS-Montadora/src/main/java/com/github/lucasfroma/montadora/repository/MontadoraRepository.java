package com.github.lucasfroma.montadora.repository;

import com.github.lucasfroma.montadora.model.Montadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MontadoraRepository extends JpaRepository<Montadora, Long> {
}
