package com.salesianos.triana.dam.EasyCar.users.controller;

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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/profile/{id}")
    public Usuario findOne(@PathVariable Long id) {
        return userEntityService.findUserById(id);
    }

    @GetMapping("/usuario/")
    public ResponseEntity<?> findAll(Pageable pageable, HttpServletRequest request) {
        Page<GetUserDto> result = userEntityService.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @GetMapping("/me")
    public GetUserDto findAuthUser (@AuthenticationPrincipal Usuario usuario) {
        return userEntityService.getAuthUser(usuario);
    }
    @PostMapping("/auth/register/admin")
    public ResponseEntity<GetUserDto> nuevoAdmin (@RequestPart("file") MultipartFile file, @Valid @RequestPart("user") CreateAdminDto newUser) throws IOException{
        Usuario saved = userEntityService.createAdmin(newUser, file);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }

    @PostMapping("/auth/register/gestor")
    public ResponseEntity<GetUserDto> nuevoGestor (@RequestPart("file") MultipartFile file, @Valid @RequestPart("user") CreateGestorDto newUser) throws IOException{
        Usuario saved = userEntityService.createGestor(newUser, file);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }

    @PostMapping("/auth/register/usuario")
    public ResponseEntity<GetUserDto> nuevoUsuario (@RequestPart("file") MultipartFile file, @Valid @RequestPart("user") CreateUsuarioDto newUser) throws IOException{
        Usuario saved = userEntityService.createUser(newUser, file);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }

    @GetMapping("/usuario/fav")
    public GetUserFavDto findUserFav (@AuthenticationPrincipal Usuario usuario) {
        return userEntityService.getUserFavs(usuario);
    }

    @PutMapping("/profile/")
    public ResponseEntity<GetUserDto> edit (@RequestPart MultipartFile file, @Valid @RequestPart("user") EditUserDto newUser, @AuthenticationPrincipal Usuario currentUser) throws IOException{
        Usuario saved = userEntityService.edit(newUser , file, currentUser);

        if (saved == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
        }
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<GetUserDto> editById (@RequestPart MultipartFile file, @Valid @RequestPart("user") EditUserDto newUser, @PathVariable Long id) throws IOException{
        Usuario saved = userEntityService.editById(newUser , file, id);

        if (saved == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException{
        userEntityService.delete(id);
        return ResponseEntity.notFound().build();
    }

}
