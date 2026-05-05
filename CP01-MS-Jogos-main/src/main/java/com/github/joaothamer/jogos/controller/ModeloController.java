package com.github.joaothamer.jogos.controller;

import com.github.joaothamer.jogos.model.Modelo;
import com.github.joaothamer.jogos.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    @Autowired
    private ModeloRepository repository;

    @PostMapping
    public ResponseEntity<Modelo> create (@RequestBody Modelo modelo){

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(modelo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> findById(@PathVariable Long id){

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Modelo>> findAll(){

        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> update(@PathVariable Long id, @RequestBody Modelo modelo){

        Optional<Modelo> optJogo = repository.findById(id);

        if (optJogo.isPresent()){
            modelo.setId(id);
            Modelo modeloAlterado = repository.save(modelo);
            return ResponseEntity.ok(modeloAlterado);
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
