package com.salesianos.triana.dam.EasyCar.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetUserDto {

    private Long id;
    private String username;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String avatar;

}
