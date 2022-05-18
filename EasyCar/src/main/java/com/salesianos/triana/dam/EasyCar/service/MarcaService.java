package com.salesianos.triana.dam.EasyCar.service;

import com.salesianos.triana.dam.EasyCar.dto.marca.CreateMarcaDto;
import com.salesianos.triana.dam.EasyCar.dto.marca.GetMarcaDto;
import com.salesianos.triana.dam.EasyCar.dto.marca.GetMarcaVehiculosDto;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MarcaService {

    Marca createMarca(CreateMarcaDto createMarcaDto, MultipartFile file) throws IOException;
    Page<GetMarcaDto> findAll(Pageable pageable);
    GetMarcaVehiculosDto findById(Long id);
    GetMarcaDto edit(CreateMarcaDto createMarcaDto, MultipartFile file, Long id) throws IOException;
    ResponseEntity<?> delete(Long id) throws IOException;
}
