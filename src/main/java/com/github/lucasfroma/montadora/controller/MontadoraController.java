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

import com.github.lucasfroma.montadora.dto.MontadoraCreateRequest;
import com.github.lucasfroma.montadora.dto.MontadoraMapper;
import com.github.lucasfroma.montadora.dto.MontadoraResponse;
import com.github.lucasfroma.montadora.dto.MontadoraUpdateRequest;
import com.github.lucasfroma.montadora.model.Montadora;
import com.github.lucasfroma.montadora.service.MontadoraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/montadoras")
public class MontadoraController {

    @Autowired
    private MontadoraService service;

    @Autowired
    private MontadoraMapper montadoraMapper;

    @PostMapping
    public ResponseEntity<MontadoraResponse> create(@Valid @RequestBody MontadoraCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(montadoraMapper.toDto(
                service.createOrUpdate(
                        montadoraMapper.toModel(dtoRequest)
                )
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MontadoraResponse> findById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(montadora -> montadoraMapper.toDto(montadora))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MontadoraResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(montadora -> montadoraMapper.toDto(montadora))
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MontadoraResponse> update(@PathVariable Long id,
                                                      @RequestBody MontadoraUpdateRequest dtoRequest) {

        if (service.findById(id).isPresent()) {
            Montadora montadora = montadoraMapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(montadoraMapper.toDto(
                    service.createOrUpdate(montadora)));
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
