package com.salesianos.triana.dam.EasyCar.users.service;

import com.salesianos.triana.dam.EasyCar.users.dto.GetUserDto;
import com.salesianos.triana.dam.EasyCar.users.dto.Usuario.CreateUsuarioDto;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserEntityService {

    Usuario createUser (CreateUsuarioDto createUsuarioDto, MultipartFile file) throws IOException;
    Page<GetUserDto> findAll(Pageable pageable);
    Usuario findById(Long id);
    Usuario edit(CreateUsuarioDto createUsuarioDto, MultipartFile file, Long id) throws IOException;
    ResponseEntity<?> delete(Long id) throws IOException;
    UserDetails loadUserByUsername(String email);
}
