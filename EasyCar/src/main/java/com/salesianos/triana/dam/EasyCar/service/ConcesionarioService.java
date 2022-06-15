package com.salesianos.triana.dam.EasyCar.service;

import com.salesianos.triana.dam.EasyCar.dto.concesionario.CreateConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.GetConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.GetConcesionarioSingleDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.GetConcesionarioVehiculosDto;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ConcesionarioService {

    GetConcesionarioDto createConcesionario(CreateConcesionarioDto createConcesionarioDto, Long idGestor);
    Page<GetConcesionarioDto> findAll(Pageable pageable);
    GetConcesionarioVehiculosDto findById(Long id);
    GetConcesionarioSingleDto edit(CreateConcesionarioDto createConcesionarioDto, Long id);
    ResponseEntity<?> delete(Long id);

    public boolean comprobarName(String name);
    public boolean comprobarId(Long id);

}
