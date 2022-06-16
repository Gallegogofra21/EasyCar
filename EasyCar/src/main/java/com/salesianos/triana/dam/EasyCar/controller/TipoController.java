package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.dto.tipo.CreateTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoVehiculosDto;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.service.TipoService;
import com.salesianos.triana.dam.EasyCar.util.PaginationLinksUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
@RequestMapping("/tipo")
@RequiredArgsConstructor
@Validated
public class TipoController {

    private final TipoService service;
    private final PaginationLinksUtil paginationLinksUtil;

    @Operation(summary = "Listar todos los tipos existentes con paginación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se listan todos los tipos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún tipo",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<?> findAll (Pageable pageable, HttpServletRequest request) {
        Page<GetTipoDto> result = service.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @Operation(summary = "Mostrar los datos de un tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se muestran todos los datos del tipo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el tipo",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetTipoVehiculosDto findOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Crear un tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se crea el tipo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<?> create (@RequestPart("file")MultipartFile file, @Valid @RequestPart("tipo")CreateTipoDto newTipo) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTipo(newTipo, file));
    }

    @Operation(summary = "Se edita un tipo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente el tipo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún tipo",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestPart("tipo") CreateTipoDto newTipo, @RequestPart("file") MultipartFile file) throws IOException{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newTipo, file, id));
    }

    @Operation(summary = "Eliminación de un tipo por su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el tipo con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado un tipo con ese id",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
