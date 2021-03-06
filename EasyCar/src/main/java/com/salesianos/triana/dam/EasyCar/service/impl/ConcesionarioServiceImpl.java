package com.salesianos.triana.dam.EasyCar.service.impl;

import com.salesianos.triana.dam.EasyCar.dto.concesionario.*;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.repo.ConcesionarioRepository;
import com.salesianos.triana.dam.EasyCar.service.ConcesionarioService;
import com.salesianos.triana.dam.EasyCar.service.StorageService;
import com.salesianos.triana.dam.EasyCar.service.VehiculoService;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.users.repo.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcesionarioServiceImpl implements ConcesionarioService {

    private final ConcesionarioRepository repository;
    private final ConverterConcesionarioDto converter;
    private final UserEntityRepository userRepository;
    private final VehiculoService vehiculoService;

    private final StorageService storageService;

    @Override
    public Page<GetConcesionarioDto> findAll(Pageable pageable) {
        Page<Concesionario> data = repository.findAll(pageable);

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Concesionario.class);
        } else {
            return data.map(converter::getConcesionarioToDto);
        }
    }

    @Override
    public GetConcesionarioVehiculosDto findById(Long id) {
        Concesionario concesionario = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));

        List<GetVehiculoDto> vehiculosConcesionario = vehiculoService.findAllByConcesionario(concesionario);

        GetConcesionarioVehiculosDto result = GetConcesionarioVehiculosDto.builder()
                .id(concesionario.getId())
                .nombre(concesionario.getNombre())
                .direccion(concesionario.getDireccion())
                .vehiculos(vehiculosConcesionario)
                .foto(concesionario.getFoto())
                .gestorId(concesionario.getUsuario().getId())
                .build();
        return result;
    }

    @Override
    public GetConcesionarioDto createConcesionario(CreateConcesionarioDto createConcesionarioDto, Long idGestor, MultipartFile file) throws IOException {

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Usuario gestor = userRepository.findById(idGestor).orElseThrow(() -> new SingleEntityNotFoundException(idGestor.toString(), Concesionario.class));
        Concesionario newConcesionario = Concesionario.builder()
                .nombre(createConcesionarioDto.getNombre())
                .direccion(createConcesionarioDto.getDireccion())
                .foto(uri)
                .vehiculos(createConcesionarioDto.getVehiculos())
                .usuario(gestor)
                .build();
        gestor.setConcesionario(repository.save(newConcesionario));
        return converter.getConcesionarioToDto(repository.save(newConcesionario));
    }

    @Override
    public GetConcesionarioSingleDto edit(CreateConcesionarioDto createConcesionarioDto, Long id, MultipartFile file) throws IOException{

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Concesionario concesionario = repository.findById(id).map(c -> {
                c.setNombre(createConcesionarioDto.getNombre());
                c.setDireccion(createConcesionarioDto.getDireccion());
                c.setFoto(uri);
                return repository.save(c);
            }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));

            return converter.getConcesionarioToSingleDto(concesionario);
    }

    @Override
    public ResponseEntity<?> delete(Long id) throws IOException {
        Concesionario concesionario = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));
        concesionario.getVehiculos().forEach(vehiculo -> {
            try {
                vehiculoService.delete(vehiculo.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Usuario gestor = concesionario.getUsuario();
        gestor.setConcesionario(null);
        storageService.deleteFile(concesionario.getFoto());
        repository.delete(concesionario);
        return ResponseEntity.noContent().build();
    }

    public boolean comprobarName(String name) {
        return repository.existsByNombre(name);
    }

    public boolean comprobarId(Long id){
        return repository.existsById(id);
    }
}
