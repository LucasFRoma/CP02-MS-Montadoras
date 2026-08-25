package com.github.lucasfroma.montadora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasfroma.montadora.model.Montadora;
import com.github.lucasfroma.montadora.repository.MontadoraRepository;

@Service
public class MontadoraService {

    @Autowired
    private MontadoraRepository repository;

    public Montadora createOrUpdate(Montadora montadora) {
        return repository.save(montadora);
    }

    public Optional<Montadora> findById(Long id) {
        return repository.findById(id);
    }

    public List<Montadora> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
