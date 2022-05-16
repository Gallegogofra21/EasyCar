package com.salesianos.triana.dam.EasyCar.users.dto;

import com.salesianos.triana.dam.EasyCar.users.dto.Gestor.CreateGestorDto;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto convertUsuarioToNewUser(Usuario p) {
        return GetUserDto.builder()
                .id(p.getId())
                .username(p.getUsername())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .telefono(p.getTelefono())
                .email(p.getEmail())
                .avatar(p.getAvatar())
                .build();
    }
}
