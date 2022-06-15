package com.salesianos.triana.dam.EasyCar.service.impl;

import com.salesianos.triana.dam.EasyCar.dto.marca.*;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.repo.MarcaRepository;
import com.salesianos.triana.dam.EasyCar.service.MarcaService;
import com.salesianos.triana.dam.EasyCar.service.StorageService;
import com.salesianos.triana.dam.EasyCar.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository repository;
    private final ConverterMarcaDto converter;
    private final StorageService storageService;
    private final VehiculoService vehiculoService;

    @Override
    public Page<GetMarcaDto> findAll(Pageable pageable) {
        Page<Marca> data = repository.findAll(pageable);

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Marca.class);
        } else {
            return data.map(converter::getMarcaToDto);
        }
    }

    @Override
    public GetMarcaVehiculosDto findById(Long id) {
        Marca marca = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Marca.class));

        List<GetVehiculoDto> vehiculosMarca = vehiculoService.findAllByMarca(marca);

        GetMarcaVehiculosDto result = GetMarcaVehiculosDto.builder()
                .id(marca.getId())
                .nombre(marca.getNombre())
                .foto(marca.getFoto())
                .vehiculos(vehiculosMarca)
                .build();
        return result;
    }

    @Override
    public Marca createMarca(CreateMarcaDto createMarcaDto, MultipartFile file) throws IOException {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Marca newMarca = Marca.builder()
                .nombre(createMarcaDto.getNombre())
                .foto(uri)
                .vehiculos(createMarcaDto.getVehiculos())
                .build();

        return repository.save(newMarca);
    }

    @Override
    public GetMarcaSingleDto edit(CreateMarcaDto createMarcaDto, MultipartFile file, Long id) throws IOException {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Marca marca = repository.findById(id).map(m -> {
            m.setNombre(createMarcaDto.getNombre());
            m.setFoto(uri);
            m.setVehiculos(createMarcaDto.getVehiculos());
            return repository.save(m);
        }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Marca.class));

        return converter.getMarcaToVehiculosDto(marca);
    }

    @Override
    public ResponseEntity<?> delete(Long id) throws IOException {
        Marca marca = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Marca.class));
        storageService.deleteFile(marca.getFoto());
        repository.delete(marca);
        return ResponseEntity.noContent().build();
    }

    public boolean comprobarName(String name) {
        return repository.existsByNombre(name);
    }
}
