package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.dto.concesionario.CreateConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.GetConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.GetConcesionarioVehiculosDto;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.service.ConcesionarioService;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/concesionario")
@RequiredArgsConstructor
@Validated
public class ConcesionarioController {

    private final ConcesionarioService service;
    private final PaginationLinksUtil paginationLinksUtil;

    @Operation(summary = "Listar todos los concesionarios existentes con paginación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se listan todos los concesionarios",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún concesionario",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<?> findAll(Pageable pageable, HttpServletRequest request) {
        Page<GetConcesionarioDto> result = service.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @Operation(summary = "Mostrar los datos de un concesionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se muestran todos los datos del concesionario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el concesionario",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetConcesionarioVehiculosDto findOne(@PathVariable Long id) { return service.findById(id); }

    @Operation(summary = "Crear un concesionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se crea el concesionario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PostMapping("/{id}")
    public ResponseEntity<?> create (@Valid @RequestPart("concesionario") CreateConcesionarioDto newConcesionario, @PathVariable Long id, @RequestPart("file")MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createConcesionario(newConcesionario, id, file));
    }

    @Operation(summary = "Se edita un concesionario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente el concesionario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún concesionario",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> edit (@PathVariable Long id, @Valid @RequestPart("concesionario") CreateConcesionarioDto newConcesionario, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newConcesionario, id, file));
    }

    @Operation(summary = "Eliminación de un concesionario por su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el concesionario con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado un concesionario con ese id",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
