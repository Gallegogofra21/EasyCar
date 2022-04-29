package com.salesianos.triana.dam.EasyCar.dto.tipo;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateTipoDto {
    private String nombre;
    private String foto;
    private List<Vehiculo> vehiculos;
}
