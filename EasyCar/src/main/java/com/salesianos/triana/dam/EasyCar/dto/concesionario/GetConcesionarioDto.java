package com.salesianos.triana.dam.EasyCar.dto.concesionario;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GetConcesionarioDto {
    private Long id;
    private String nombre;
    private String direccion;
    private int numVehiculos;
    private Long usuarioId;
}
