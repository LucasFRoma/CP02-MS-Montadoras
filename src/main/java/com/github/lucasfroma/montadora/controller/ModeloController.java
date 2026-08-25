package com.github.lucasfroma.montadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasfroma.montadora.dto.ModeloCreateRequest;
import com.github.lucasfroma.montadora.dto.ModeloMapper;
import com.github.lucasfroma.montadora.dto.ModeloResponse;
import com.github.lucasfroma.montadora.dto.ModeloUpdateRequest;
import com.github.lucasfroma.montadora.model.Modelo;
import com.github.lucasfroma.montadora.service.ModeloService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    @Autowired
    private ModeloService service;

    @Autowired
    private ModeloMapper modeloMapper;

    @PostMapping
    public ResponseEntity<ModeloResponse> create(@Valid @RequestBody ModeloCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modeloMapper.toDto(
                service.createOrUpdate(
                        modeloMapper.toModel(dtoRequest)
                )
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloResponse> findById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(modelo -> modeloMapper.toDto(modelo))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ModeloResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(modelo -> modeloMapper.toDto(modelo))
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloResponse> update(@PathVariable Long id,
                                                   @RequestBody ModeloUpdateRequest dtoRequest) {

        if (service.findById(id).isPresent()) {
            Modelo modelo = modeloMapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(modeloMapper.toDto(
                    service.createOrUpdate(modelo)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
