package com.github.joaothamer.jogos.controller;


import com.github.joaothamer.jogos.model.Montadora;
import com.github.joaothamer.jogos.repository.MontadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/montadoras")
public class MontadoraController {

    @Autowired
    private MontadoraRepository repository;

    @PostMapping
    public ResponseEntity<Montadora> create (@RequestBody Montadora montadora){

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(montadora));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Montadora> findById(@PathVariable Long id){

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Montadora>> findAll(){

        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Montadora> update(@PathVariable Long id, @RequestBody Montadora montadora){

        Optional<Montadora> optMontadora = repository.findById(id);

        if (optMontadora.isPresent()){
            montadora.setId(id);
            Montadora montadoraAlterado = repository.save(montadora);
            return ResponseEntity.ok(montadoraAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
