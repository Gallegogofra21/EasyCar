package com.salesianos.triana.dam.EasyCar.dto.concesionario;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateConcesionarioDto {

    private String nombre;
    private String direccion;
    private List<Vehiculo> vehiculos;
    private Usuario usuario;
}
