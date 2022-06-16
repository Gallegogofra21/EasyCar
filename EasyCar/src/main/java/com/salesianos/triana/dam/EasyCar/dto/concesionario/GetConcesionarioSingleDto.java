package com.salesianos.triana.dam.EasyCar.dto.concesionario;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GetConcesionarioSingleDto {

    private Long id;
    private String nombre;
    private String direccion;
    private String foto;
}
