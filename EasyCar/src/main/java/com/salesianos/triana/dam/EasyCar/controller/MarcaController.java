package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.dto.marca.CreateMarcaDto;
import com.salesianos.triana.dam.EasyCar.dto.marca.GetMarcaDto;
import com.salesianos.triana.dam.EasyCar.dto.marca.GetMarcaVehiculosDto;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.service.MarcaService;
import com.salesianos.triana.dam.EasyCar.util.PaginationLinksUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Listar todas las marcas existentes con paginación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se listan todas las marcas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna marca",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<?> findAll (Pageable pageable, HttpServletRequest request) {
        Page<GetMarcaDto> result = service.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @Operation(summary = "Mostrar los datos de una marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se muestran todos los datos de la marca",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la marca",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetMarcaVehiculosDto findOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Crear una marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se crea la marca",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<?> create(@RequestPart("file")MultipartFile file, @Valid @RequestPart("marca")CreateMarcaDto newMarca) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createMarca(newMarca, file));
    }

    @Operation(summary = "Se edita una marca existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente la marca",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna marca",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> edit (@PathVariable Long id, @Valid @RequestPart("marca") CreateMarcaDto newMarca, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newMarca, file, id));
    }

    @Operation(summary = "Eliminación de una marca por su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la marca con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna marca con ese id",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
