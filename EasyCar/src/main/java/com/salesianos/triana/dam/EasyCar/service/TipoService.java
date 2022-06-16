package com.salesianos.triana.dam.EasyCar.service;

import com.salesianos.triana.dam.EasyCar.dto.tipo.CreateTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoVehiculosDto;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TipoService {

    GetTipoDto createTipo(CreateTipoDto createTipoDto, MultipartFile file) throws IOException;
    Page<GetTipoDto> findAll(Pageable pageable);
    GetTipoVehiculosDto findById(Long id);
    GetTipoDto edit(CreateTipoDto createTipoDto, MultipartFile file, Long id) throws IOException;
    ResponseEntity<?> delete(Long id) throws IOException;

    public boolean comprobarName(String name);
}
