package com.salesianos.triana.dam.EasyCar.dto.marca;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueNameMarca;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class CreateMarcaDto {

    @NotBlank(message = "{marca.nombre.blank}")
    @UniqueNameMarca(message = "{marca.nombre.unico}")
    private String nombre;
    private String foto;
    @Builder.Default
    private List<Vehiculo> vehiculos = new ArrayList<>();
}
