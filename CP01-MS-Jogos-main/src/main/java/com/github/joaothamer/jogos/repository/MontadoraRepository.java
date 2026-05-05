package com.github.joaothamer.jogos.repository;

import com.github.joaothamer.jogos.model.Montadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MontadoraRepository extends JpaRepository<Montadora, Long> {
}
