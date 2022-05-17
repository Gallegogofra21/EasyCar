package com.salesianos.triana.dam.EasyCar.service;

import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
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

    GetVehiculoDto createVehiculo(CreateVehiculoDto createVehiculoDto, MultipartFile file, Long id) throws IOException;
    Page<GetVehiculoDto> findAll(Pageable pageable);
    GetVehiculoDto findById(Long id);
    List<GetVehiculoDto> findAllByConcesionario(Concesionario concesionario);
    List<GetVehiculoDto> findAllByMarca(Marca marca);
    List<GetVehiculoDto> findAllByTipo(Tipo tipo);
    Vehiculo edit(CreateVehiculoDto createVehiculoDto, MultipartFile file, Usuario usuario, Long id);
    ResponseEntity<?> delete(Long id, Usuario usuario) throws IOException;
}
