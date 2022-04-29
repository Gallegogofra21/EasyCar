package com.salesianos.triana.dam.EasyCar.service.impl;

import com.salesianos.triana.dam.EasyCar.dto.tipo.ConverterTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoDto;
import com.salesianos.triana.dam.EasyCar.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.repo.TipoRepository;
import com.salesianos.triana.dam.EasyCar.service.StorageService;
import com.salesianos.triana.dam.EasyCar.service.TipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoServiceImpl implements TipoService {

    private final TipoRepository repository;
    private final ConverterTipoDto converter;
    private final StorageService storageService;

    @Override
    public List<GetTipoDto> findAll() {
        List<Tipo> data = repository.findAll();

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Tipo.class);
        } else {
            return data.stream().map(converter::getTipoToDto).collect(Collectors.toList());
        }
    }

    @Override
    public Tipo findById(Long id)
}
