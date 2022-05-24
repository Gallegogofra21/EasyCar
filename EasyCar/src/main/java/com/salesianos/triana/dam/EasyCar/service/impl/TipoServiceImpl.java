package com.salesianos.triana.dam.EasyCar.service.impl;

import com.salesianos.triana.dam.EasyCar.dto.tipo.ConverterTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.CreateTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoVehiculosDto;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.repo.TipoRepository;
import com.salesianos.triana.dam.EasyCar.service.StorageService;
import com.salesianos.triana.dam.EasyCar.service.TipoService;
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
public class TipoServiceImpl implements TipoService {

    private final TipoRepository repository;
    private final ConverterTipoDto converter;
    private final StorageService storageService;
    private final VehiculoService vehiculoService;

    @Override
    public Page<GetTipoDto> findAll(Pageable pageable) {
        Page<Tipo> data = repository.findAll(pageable);

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Tipo.class);
        } else {
            return data.map(converter::getTipoToDto);
        }
    }

    @Override
    public GetTipoVehiculosDto findById(Long id) {
        Tipo tipo = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Tipo.class));

        List<GetVehiculoDto> vehiculosTipo = vehiculoService.findAllByTipo(tipo);

        GetTipoVehiculosDto result = GetTipoVehiculosDto.builder()
                .id(tipo.getId())
                .nombre(tipo.getNombre())
                .foto(tipo.getFoto())
                .vehiculos(vehiculosTipo)
                .build();
        return result;
    }

    @Override
    public GetTipoDto createTipo (CreateTipoDto createTipoDto, MultipartFile file) throws IOException {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Tipo newTipo = Tipo.builder()
                .nombre(createTipoDto.getNombre())
                .foto(uri)
                .build();
        return converter.getTipoToDto(repository.save(newTipo));
    }

    @Override
    public GetTipoDto edit (CreateTipoDto createTipoDto, MultipartFile file, Long id) throws IOException {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Tipo tipo = repository.findById(id).map(t -> {
            t.setNombre(createTipoDto.getNombre());
            t.setFoto(uri);
            t.setVehiculos(createTipoDto.getVehiculos());
            return repository.save(t);
        }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Tipo.class));

        return converter.getTipoToDto(tipo);
    }

    @Override
    public ResponseEntity<?> delete (Long id) throws IOException {
        Tipo tipo = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Tipo.class));
        storageService.deleteFile(tipo.getFoto());
        repository.delete(tipo);
        return ResponseEntity.noContent().build();
    }
}
