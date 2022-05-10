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
import com.salesianos.triana.dam.EasyCar.users.repo.UserEntityRepository;
import com.salesianos.triana.dam.EasyCar.users.service.impl.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcesionarioServiceImpl implements ConcesionarioService {

    private final ConcesionarioRepository repository;
    private final ConverterConcesionarioDto converter;
    private final UserEntityRepository userRepository;

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
    public Concesionario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));
    }

    @Override
    public Concesionario createConcesionario(CreateConcesionarioDto createConcesionarioDto, Long idGestor) {
        Usuario gestor = userRepository.findById(idGestor).orElseThrow(() -> new SingleEntityNotFoundException(idGestor.toString(), Concesionario.class));
        Concesionario newConcesionario = Concesionario.builder()
                .nombre(createConcesionarioDto.getNombre())
                .direccion(createConcesionarioDto.getDireccion())
                .vehiculos(createConcesionarioDto.getVehiculos())
                .usuario(gestor)
                .build();

        gestor.setConcesionario(repository.save(newConcesionario));
        return repository.save(newConcesionario);
    }

    @Override
    public Concesionario edit(CreateConcesionarioDto createConcesionarioDto, Long id) {
        Concesionario concesionario = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));
            return repository.findById(id).map(c -> {
                c.setNombre(createConcesionarioDto.getNombre());
                c.setDireccion(createConcesionarioDto.getDireccion());
                c.setVehiculos(createConcesionarioDto.getVehiculos());
                return repository.save(c);
            }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Concesionario concesionario = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Concesionario.class));
        repository.delete(concesionario);
        return ResponseEntity.noContent().build();
    }
}
