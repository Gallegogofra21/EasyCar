package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.CreateVehiculoDto;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.service.VehiculoService;
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
import java.util.Optional;

@RestController
@RequestMapping("/vehiculo")
@RequiredArgsConstructor
@Validated
public class VehiculoController {

    private final VehiculoService service;
    private final PaginationLinksUtil paginationLinksUtil;

    @Operation(summary = "Listar todos los vehiculos existentes con paginación y filtrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Se listan todos los vehiculos",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
            description = "No se ha encontrado ningún vehiculo",
            content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<?> findAll(@RequestParam("marca") Optional<String> marca,
                                     @RequestParam("modelo") Optional<String> modelo,
                                     @RequestParam("precioMax") Optional<Float> precioMax,
                                     @RequestParam("precioMin") Optional<Float> precioMin,
                                     @RequestParam("tipo") Optional<String> tipo,
                                     Pageable pageable, HttpServletRequest request) {
        Page<GetVehiculoDto> result = service.findAll(pageable, marca, modelo, precioMax, precioMin, tipo);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @Operation(summary = "Listar todos los vehiculos de una marca con paginación y filtrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se listan todos los vehiculos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún vehiculo",
                    content = @Content),
    })
    @GetMapping("/marca/{nombre}")
    public ResponseEntity<?> findAllByMarca(@PathVariable String nombre, Pageable pageable, HttpServletRequest request) {
        Page<GetVehiculoDto> result = service.findAllVehiculosByMarca(nombre, pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @Operation(summary = "Listar todos los vehiculos de un tipo con paginación y filtrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se listan todos los vehiculos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún vehiculo",
                    content = @Content),
    })
    @GetMapping("/tipo/{id}")
    public ResponseEntity<?> findAllByTipo(@PathVariable Long id, Pageable pageable, HttpServletRequest request) {
        Page<GetVehiculoDto> result = service.findAllVehiculosByTipo(id, pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @Operation(summary = "Mostrar los datos de un vehículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se muestran todos los datos del vehiculo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el vehiculo",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetVehiculoDto findOne(@PathVariable Long id) {
        return service.findById(id);
    }


    @Operation(summary = "Crear un vehiculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se crea el vehiculo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PostMapping("/{id}")
    public ResponseEntity<?> create(@RequestPart("file1")MultipartFile file1, @RequestPart("file2")MultipartFile file2, @RequestPart("file3")MultipartFile file3, @RequestPart("file4")MultipartFile file4, @Valid @RequestPart("vehiculo")CreateVehiculoDto newVehiculo, @PathVariable Long id) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createVehiculo(newVehiculo, file1, file2, file3, file4, id));
    }

    @Operation(summary = "Se edita un vehiculo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente el vehiculo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún vehiculo",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestPart("vehiculo") CreateVehiculoDto newVehiculo, @RequestPart("file1")MultipartFile file1,  @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newVehiculo, file1, usuario, id));
    }

    @Operation(summary = "Eliminación de un vehiculo por su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el vehiculo con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado un vehiculo con ese id",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) throws IOException {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/fav")
    public ResponseEntity<?> addVehiculoToFav(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        service.addVehiculoToFav(id, usuario);
        return ResponseEntity.ok().build();
    }
}
