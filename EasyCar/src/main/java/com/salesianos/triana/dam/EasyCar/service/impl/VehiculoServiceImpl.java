package com.salesianos.triana.dam.EasyCar.service.impl;

import com.salesianos.triana.dam.EasyCar.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.repo.VehiculoRepository;
import com.salesianos.triana.dam.EasyCar.security.dto.vehiculo.ConverterVehiculoDto;
import com.salesianos.triana.dam.EasyCar.security.dto.vehiculo.CreateVehiculoDto;
import com.salesianos.triana.dam.EasyCar.security.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.service.StorageService;
import com.salesianos.triana.dam.EasyCar.service.VehiculoService;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.users.service.impl.UserEntityService;
import lombok.RequiredArgsConstructor;
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
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository repository;
    private final StorageService storageService;
    private final UserEntityService userEntityService;
    private final ConverterVehiculoDto converter;

    @Override
    public Vehiculo createVehiculo(CreateVehiculoDto createVehiculoDto, MultipartFile file, Usuario usuario) throws IOException {
        String filename = storageService.store(file);

        Vehiculo newVehiculo = Vehiculo.builder()
                .version(createVehiculoDto.getVersion())
                .fechaMatriculacion(createVehiculoDto.getFechaMatriculacion())
                .kilometraje(createVehiculoDto.getKilometraje())
                .potencia(createVehiculoDto.getPotencia())
                .marchas(createVehiculoDto.getMarchas())
                .precio(createVehiculoDto.getPrecio())
                .marca(createVehiculoDto.getMarca())
                .tipo(createVehiculoDto.getTipo())
                .foto1(createVehiculoDto.getFoto1())
                .llantas(createVehiculoDto.getLlantas())
                .distribucion(createVehiculoDto.getDistribucion())
                .procedencia(createVehiculoDto.getProcedencia())
                .traccion(createVehiculoDto.getTraccion())
                .build();

        BufferedImage img = ImageIO.read(file.getInputStream());
        OutputStream out = Files.newOutputStream(storageService.load(filename));

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(storageService.store(file))
                .toUriString();
        newVehiculo.setFoto1(uri);

        return repository.save(newVehiculo);
    }

    @Override
    public List<GetVehiculoDto> findAll() {
        List<Vehiculo> data = repository.findAll();

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Vehiculo.class);
        } else {
            return data.stream().map(converter::getVehiculoToDto).collect(Collectors.toList());
        }
    }

    @Override
    public Vehiculo findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Vehiculo.class));
    }

    @Override
    public Vehiculo edit(CreateVehiculoDto createVehiculoDto, MultipartFile file, Usuario usuario, Long id) {
        Vehiculo vehiculo = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Vehiculo.class));

        if(vehiculo.getUsuario().getId().equals(usuario.getId()) && vehiculo.getUsuario().get)
    }

    @Override
    public ResponseEntity<?> delete(Long id, Usuario usuario) throws IOException {
        return null;
    }
}
