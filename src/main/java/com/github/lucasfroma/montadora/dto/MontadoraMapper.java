package com.github.lucasfroma.montadora.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.github.lucasfroma.montadora.model.Montadora;

@Component
public class MontadoraMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Montadora toModel(MontadoraCreateRequest dto) {
        return modelMapper.map(dto, Montadora.class);
    }

    public MontadoraResponse toDto(Montadora entity) {
        return modelMapper.map(entity, MontadoraResponse.class);
    }

    public Montadora toModel(Long id, MontadoraUpdateRequest dto) {
        Montadora montadora = modelMapper.map(dto, Montadora.class);
        montadora.setId(id);
        return montadora;
    }
}
