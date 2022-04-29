package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.CreateVehiculoDto;
import com.salesianos.triana.dam.EasyCar.service.VehiculoService;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/vehiculo")
@RequiredArgsConstructor
@Validated
public class VehiculoController {

    private final VehiculoService service;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public Vehiculo findOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestPart("file")MultipartFile file, @Valid @RequestPart("vehiculo")CreateVehiculoDto newVehiculo, @AuthenticationPrincipal Usuario usuario) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createVehiculo(newVehiculo, file, usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestPart("vehiculo") CreateVehiculoDto newVehiculo, @RequestPart("file") MultipartFile file, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newVehiculo, file, usuario, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) throws IOException {
        service.delete(id, usuario);
        return ResponseEntity.notFound().build();
    }
}
