package com.salesianos.triana.dam.EasyCar.service.impl;

import com.salesianos.triana.dam.EasyCar.dto.concesionario.ConverterConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.CreateConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.GetConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.repo.ConcesionarioRepository;
import com.salesianos.triana.dam.EasyCar.service.ConcesionarioService;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcesionarioServiceImpl implements ConcesionarioService {

    private final ConcesionarioRepository repository;
    private final ConverterConcesionarioDto converter;

    @Override
    public List<GetConcesionarioDto> findAll() {
        List<Concesionario> data = repository.findAll();

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Concesionario.class);
        } else {
            return data.stream().map(converter::getConcesionarioToDto).collect(Collectors.toList());
        }
    }

    @Override
    public Concesionario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));
    }

    @Override
    public Concesionario createConcesionario(CreateConcesionarioDto createConcesionarioDto, Usuario usuario) {
        Concesionario newConcesionario = Concesionario.builder()
                .nombre(createConcesionarioDto.getNombre())
                .direccion(createConcesionarioDto.getDireccion())
                .vehiculos(createConcesionarioDto.getVehiculos())
                .build();
        return repository.save(newConcesionario);
    }

    @Override
    public Concesionario edit(CreateConcesionarioDto createConcesionarioDto, Usuario usuario, Long id) {
        Concesionario concesionario = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));

        if(concesionario.getUsuario().getId().equals(usuario.getId())){
            return repository.findById(id).map(c -> {
                c.setNombre(createConcesionarioDto.getNombre());
                c.setDireccion(createConcesionarioDto.getDireccion());
                c.setVehiculos(createConcesionarioDto.getVehiculos());
                return repository.save(c);
            }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));
        }
        throw new SingleEntityNotFoundException(id.toString(), Concesionario.class);
    }

    @Override
    public ResponseEntity<?> delete(Long id, Usuario usuario) throws IOException {
        Concesionario concesionario = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));
        repository.delete(concesionario);
        return ResponseEntity.noContent().build();
    }
}
