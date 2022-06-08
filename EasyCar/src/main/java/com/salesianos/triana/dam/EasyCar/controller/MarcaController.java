package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.dto.marca.CreateMarcaDto;
import com.salesianos.triana.dam.EasyCar.dto.marca.GetMarcaDto;
import com.salesianos.triana.dam.EasyCar.dto.marca.GetMarcaVehiculosDto;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.service.MarcaService;
import com.salesianos.triana.dam.EasyCar.util.PaginationLinksUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
@Validated
public class MarcaController {

    private final MarcaService service;
    private final PaginationLinksUtil paginationLinksUtil;

    @GetMapping("/")
    public ResponseEntity<?> findAll (Pageable pageable, HttpServletRequest request) {
        Page<GetMarcaDto> result = service.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @GetMapping("/{id}")
    public GetMarcaVehiculosDto findOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestPart("file")MultipartFile file, @Valid @RequestPart("marca")CreateMarcaDto newMarca) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createMarca(newMarca, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit (@PathVariable Long id, @Valid @RequestPart("marca") CreateMarcaDto newMarca, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newMarca, file, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
