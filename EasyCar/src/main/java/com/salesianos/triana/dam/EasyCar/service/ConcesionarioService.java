package com.salesianos.triana.dam.EasyCar.service;

import com.salesianos.triana.dam.EasyCar.dto.concesionario.CreateConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.GetConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ConcesionarioService {

    Concesionario createConcesionario(CreateConcesionarioDto createConcesionarioDto, Long id) throws IOException;
    List<GetConcesionarioDto> findAll();
    Concesionario findById(Long id);
    Concesionario edit(CreateConcesionarioDto createConcesionarioDto, Usuario usuario, Long id);
    ResponseEntity<?> delete(Long id, Usuario usuario) throws IOException;
}
