package com.github.lucasfroma.montadora.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.github.lucasfroma.montadora.model.Modelo;

@Component
public class ModeloMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Modelo toModel(ModeloCreateRequest dto) {
        return modelMapper.map(dto, Modelo.class);
    }

    public ModeloResponse toDto(Modelo entity) {
        return modelMapper.map(entity, ModeloResponse.class);
    }

    public Modelo toModel(Long id, ModeloUpdateRequest dto) {
        Modelo modelo = modelMapper.map(dto, Modelo.class);
        modelo.setId(id);
        return modelo;
    }
}
