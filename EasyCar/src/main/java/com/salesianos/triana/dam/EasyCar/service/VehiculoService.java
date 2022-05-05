package com.salesianos.triana.dam.EasyCar.service;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.CreateVehiculoDto;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface VehiculoService {

    Vehiculo createVehiculo(CreateVehiculoDto createVehiculoDto, MultipartFile file, Usuario usuario) throws IOException;
    Page<GetVehiculoDto> findAll(Pageable pageable);
    Vehiculo findById(Long id);
    Vehiculo edit(CreateVehiculoDto createVehiculoDto, MultipartFile file, Usuario usuario, Long id);
    ResponseEntity<?> delete(Long id, Usuario usuario) throws IOException;
}
