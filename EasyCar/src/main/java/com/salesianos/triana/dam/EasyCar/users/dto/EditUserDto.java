package com.salesianos.triana.dam.EasyCar.users.dto;

import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueEmailUser;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueUsernameUser;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditUserDto {

    private String username;

    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private String password;
    private String password2;
    private String avatar;
    private String rol;
}