package com.salesianos.triana.dam.EasyCar.users.controller;

import com.salesianos.triana.dam.EasyCar.users.dto.CreateUserDto;
import com.salesianos.triana.dam.EasyCar.users.dto.GetUserDto;
import com.salesianos.triana.dam.EasyCar.users.dto.UserDtoConverter;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.users.repo.UserEntityRepository;
import com.salesianos.triana.dam.EasyCar.users.service.impl.UserEntityService;
import com.salesianos.triana.dam.EasyCar.util.PaginationLinksUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/auth/register")
    public ResponseEntity<GetUserDto> nuevoUser (@RequestPart("file") MultipartFile file, @Valid @RequestPart("user") CreateUserDto newUser) {
        Usuario saved = userEntityService.saveUser(newUser, file);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }

    @PutMapping("/profile/me")
    public ResponseEntity<GetUserDto> editPost (@RequestPart("file") MultipartFile file, @Valid @RequestPart("user") CreateUserDto newUser, @AuthenticationPrincipal Usuario currentUser) throws IOException {
        Usuario saved = userEntityService.edit(newUser, file, currentUser);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }
}
