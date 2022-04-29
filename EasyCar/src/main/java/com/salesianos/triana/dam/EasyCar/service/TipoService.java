package com.salesianos.triana.dam.EasyCar.service;

import com.salesianos.triana.dam.EasyCar.dto.tipo.CreateTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoDto;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TipoService {

    Tipo createTipo(CreateTipoDto createTipoDto, MultipartFile file) throws IOException;
    List<GetTipoDto> findAll();
    Tipo findById(Long id);
    Tipo edit(CreateTipoDto createTipoDto, MultipartFile file, Long id) throws IOException;
    ResponseEntity<?> delete(Long id) throws IOException;
}
