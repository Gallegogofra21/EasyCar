package com.salesianos.triana.dam.EasyCar.dto.marca;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class CreateMarcaDto {

    private String nombre;
    private String foto;
    @Builder.Default
    private List<Vehiculo> vehiculos = new ArrayList<>();
}
