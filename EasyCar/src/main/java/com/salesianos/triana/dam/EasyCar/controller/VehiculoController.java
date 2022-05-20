package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.CreateVehiculoDto;
import com.salesianos.triana.dam.EasyCar.service.VehiculoService;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.util.PaginationLinksUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/vehiculo")
@RequiredArgsConstructor
@Validated
public class VehiculoController {

    private final VehiculoService service;
    private final PaginationLinksUtil paginationLinksUtil;

    @GetMapping("/")
    public ResponseEntity<?> findAll(Pageable pageable, HttpServletRequest request) {
        Page<GetVehiculoDto> result = service.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @GetMapping("/{id}")
    public GetVehiculoDto findOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@RequestPart("file")MultipartFile file, @Valid @RequestPart("vehiculo")CreateVehiculoDto newVehiculo, @PathVariable Long id) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createVehiculo(newVehiculo, file, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestPart("vehiculo") CreateVehiculoDto newVehiculo, @RequestPart("file") MultipartFile file, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newVehiculo, file, usuario, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) throws IOException {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
