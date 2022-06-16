package com.salesianos.triana.dam.EasyCar.users.controller;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.users.dto.Admin.CreateAdminDto;
import com.salesianos.triana.dam.EasyCar.users.dto.EditUserDto;
import com.salesianos.triana.dam.EasyCar.users.dto.Gestor.CreateGestorDto;
import com.salesianos.triana.dam.EasyCar.users.dto.GetUserDto;
import com.salesianos.triana.dam.EasyCar.users.dto.GetUserFavDto;
import com.salesianos.triana.dam.EasyCar.users.dto.UserDtoConverter;
import com.salesianos.triana.dam.EasyCar.users.dto.Usuario.CreateUsuarioDto;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.users.repo.UserEntityRepository;
import com.salesianos.triana.dam.EasyCar.users.service.UserEntityService;
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
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserEntityService userEntityService;
    private final UserEntityRepository userEntityRepository;
    private final UserDtoConverter userDtoConverter;
    private final PaginationLinksUtil paginationLinksUtil;

    @Operation(summary = "Mostrar los datos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se muestran todos los datos del usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario",
                    content = @Content),
    })
    @GetMapping("/profile/{id}")
    public Usuario findOne(@PathVariable Long id) {
        return userEntityService.findUserById(id);
    }

    @Operation(summary = "Listar todos los usuarios existentes con paginación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se listan todos los usuarios",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún usuario",
                    content = @Content),
    })
    @GetMapping("/usuario/")
    public ResponseEntity<?> findAll(Pageable pageable, HttpServletRequest request) {
        Page<GetUserDto> result = userEntityService.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @Operation(summary = "Mostrar los datos del usuario loggeado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se muestran todos los datos del usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario",
                    content = @Content),
    })
    @GetMapping("/me")
    public GetUserDto findAuthUser (@AuthenticationPrincipal Usuario usuario) {
        return userEntityService.getAuthUser(usuario);
    }

    @Operation(summary = "Crear un usuario administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se crea el usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PostMapping("/auth/register/admin")
    public ResponseEntity<GetUserDto> nuevoAdmin (@RequestPart("file") MultipartFile file, @Valid @RequestPart("user") CreateAdminDto newUser) throws IOException{
        Usuario saved = userEntityService.createAdmin(newUser, file);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }

    @Operation(summary = "Crear un usuario gestor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se crea el usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PostMapping("/auth/register/gestor")
    public ResponseEntity<GetUserDto> nuevoGestor (@RequestPart("file") MultipartFile file, @Valid @RequestPart("user") CreateGestorDto newUser) throws IOException{
        Usuario saved = userEntityService.createGestor(newUser, file);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }

    @Operation(summary = "Crear un usuario comun")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se crea el usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PostMapping("/auth/register/usuario")
    public ResponseEntity<GetUserDto> nuevoUsuario (@RequestPart("file") MultipartFile file, @Valid @RequestPart("user") CreateUsuarioDto newUser) throws IOException{
        GetUserDto saved = userEntityService.createUser(newUser, file);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntityService.createUser(newUser, file));
    }

    @GetMapping("/usuario/fav")
    public GetUserFavDto findUserFav (@AuthenticationPrincipal Usuario usuario) {
        return userEntityService.getUserFavs(usuario);
    }

    @Operation(summary = "Se edita el usuario loggeado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente el usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún usuario",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PutMapping("/profile/")
    public ResponseEntity<GetUserDto> edit (@RequestPart MultipartFile file, @Valid @RequestPart("user") EditUserDto newUser, @AuthenticationPrincipal Usuario currentUser) throws IOException{
        Usuario saved = userEntityService.edit(newUser , file, currentUser);

        if (saved == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
        }
    }

    @Operation(summary = "Se edita un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente el usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún usuario",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "La estructura de la petición estaba mal formulada",
                    content = @Content),
    })
    @PutMapping("/usuario/{id}")
    public ResponseEntity<GetUserDto> editById (@RequestPart MultipartFile file, @Valid @RequestPart("user") EditUserDto newUser, @PathVariable Long id) throws IOException{
        Usuario saved = userEntityService.editById(newUser , file, id);

        if (saved == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
        }
    }

    @Operation(summary = "Eliminación de un usuario por su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el usuario con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado un usuario con ese id",
                    content = @Content),
    })
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException{
        userEntityService.delete(id);
        return ResponseEntity.notFound().build();
    }

}
