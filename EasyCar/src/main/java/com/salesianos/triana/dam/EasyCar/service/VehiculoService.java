package com.salesianos.triana.dam.EasyCar.service;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDetails;
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
import java.util.Optional;

public interface VehiculoService {

    GetVehiculoDto createVehiculo(CreateVehiculoDto createVehiculoDto, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, Long id) throws IOException;

    Page<GetVehiculoDto> findAll(Pageable pageable,
                                 Optional<String> marca,
                                 Optional<String> modelo,
                                 Optional<Float> precioMax,
                                 Optional<Float> precioMin,
                                 Optional<String> tipo);

    GetVehiculoDetails findById(Long id);
    List<GetVehiculoDto> findAllByConcesionario(Concesionario concesionario);
    List<GetVehiculoDto> findAllByMarca(Marca marca);
    List<GetVehiculoDto> findAllByTipo(Tipo tipo);

    Page<GetVehiculoDto> findAllVehiculosByMarca(Long id, Pageable pageable);
    Page<GetVehiculoDto> findAllVehiculosByTipo(Long id, Pageable pageable);
    GetVehiculoDto edit(CreateVehiculoDto createVehiculoDto, MultipartFile file, Usuario usuario, Long id);
    ResponseEntity<?> delete(Long id) throws IOException;
    ResponseEntity<?> addVehiculoToFav(Long id, Usuario user);
}
